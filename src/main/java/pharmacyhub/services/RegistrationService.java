package pharmacyhub.services;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.users.DrugstoreAdmin;
import pharmacyhub.domain.users.Patient;
import pharmacyhub.domain.users.Role;
import pharmacyhub.domain.users.Supplier;
import pharmacyhub.domain.users.SystemAdmin;
import pharmacyhub.domain.users.User;
import pharmacyhub.dto.UserRegistrationDto;
import pharmacyhub.repositories.DrugstoreRepository;
import pharmacyhub.repositories.LocationRepository;
import pharmacyhub.repositories.users.UserRepository;
import pharmacyhub.utils.RadnomGeneratorUtil;

@Service
public class RegistrationService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private DrugstoreRepository drugstoreRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserNotificationService userNotificationService;

	public boolean registerUser(UserRegistrationDto requestUser) throws Exception {

		User user = userRepository.findByEmail(requestUser.getEmail());

		if (user != null) {
			throw new Exception("Email already taken!");
		}

		switch (requestUser.getType()) {
		case Patient:
			savePatient(requestUser);
			break;
		case Supplier:
			saveSupplier(requestUser);
			break;
		case DrugstoreAdmin:
			saveDrugstoreAdmin(requestUser);
			break;
		case SystemAdmin:
			saveSystemAdmin(requestUser);
			break;
		default:
		}

		return true;

	}

	private void saveSystemAdmin(UserRegistrationDto requestUser) throws MessagingException {
		SystemAdmin systemAdmin = getSystemAdminFromUserRegistrationDto(requestUser);
		String initialPassword = updateUserWithInitialPasswordAndEncode(systemAdmin);
		systemAdmin.setStatus(true);
		systemAdmin = userRepository.save(systemAdmin);

		userNotificationService.sendEmployeeInitialPassword(systemAdmin.getEmail(), initialPassword);
	}

	private void saveDrugstoreAdmin(UserRegistrationDto requestUser) throws Exception {
		DrugstoreAdmin drugstoreAdmin = getDrugstoreAdminFromUserRegistrationDto(requestUser);
		String initialPassword = updateUserWithInitialPasswordAndEncode(drugstoreAdmin);
		drugstoreAdmin.setStatus(true);
		drugstoreAdmin = userRepository.save(drugstoreAdmin);

		userNotificationService.sendEmployeeInitialPassword(drugstoreAdmin.getEmail(), initialPassword);
	}

	private void saveSupplier(UserRegistrationDto requestUser) throws MessagingException {
		Supplier supplier = getSupplierFromUserRegistrationDto(requestUser);
		String initialPassword = updateUserWithInitialPasswordAndEncode(supplier);
		supplier.setStatus(true);
		supplier = userRepository.save(supplier);

		userNotificationService.sendEmployeeInitialPassword(supplier.getEmail(), initialPassword);
	}
	
	private String updateUserWithInitialPasswordAndEncode(User user) {
		String initialPassword = RadnomGeneratorUtil.generateEmployeePassword();
		String encodedInitialPassword = passwordEncoder.encode(initialPassword);
		user.setPassword(encodedInitialPassword);
		return initialPassword;
	}

	private void savePatient(UserRegistrationDto requestUser) throws Exception {

		Patient patient = getPatientFromUserRegistrationDto(requestUser);

		patient.setPassword(passwordEncoder.encode(patient.getPassword()));

		String activationCode = RadnomGeneratorUtil.generateActivationCode();
		patient.setActivationCode(activationCode);
		patient.setStatus(false);
		patient = userRepository.save(patient);

		if (!requestUser.getPassword().equals(requestUser.getRepeatedPassword())) {
			throw new Exception("Passwords do not match");
		}

		userNotificationService.sendActivationCode(patient.getEmail(), activationCode);
	}

	private DrugstoreAdmin getDrugstoreAdminFromUserRegistrationDto(UserRegistrationDto requestUser) throws Exception {
		DrugstoreAdmin drugstoreAdmin = new DrugstoreAdmin();
		updateUserFromRequestDto(drugstoreAdmin, requestUser);
		Drugstore drugstore = drugstoreRepository.findById(requestUser.getDrugstoreId()).orElse(null);
		if (drugstore == null) {
			throw new Exception("Drugstore with that id doesn't exist.");
		}
		drugstoreAdmin.setDrugstore(drugstore);
		List<Role> roles = roleService.findByName("DrugstoreAdmin");
		drugstoreAdmin.setRoles(roles);
		return drugstoreAdmin;
	}

	private Supplier getSupplierFromUserRegistrationDto(UserRegistrationDto requestUser) {
		Supplier supplier = new Supplier();
		updateUserFromRequestDto(supplier, requestUser);
		List<Role> roles = roleService.findByName("Supplier");
		supplier.setRoles(roles);
		return supplier;
	}

	private Patient getPatientFromUserRegistrationDto(UserRegistrationDto requestUser) {
		Patient patient = new Patient();
		updateUserFromRequestDto(patient, requestUser);
		List<Role> roles = roleService.findByName("Patient");
		patient.setRoles(roles);
		return patient;
	}

	private SystemAdmin getSystemAdminFromUserRegistrationDto(UserRegistrationDto requestUser) {
		SystemAdmin systemAdmin = new SystemAdmin();
		updateUserFromRequestDto(systemAdmin, requestUser);
		List<Role> roles = roleService.findByName("SystemAdmin");
		systemAdmin.setRoles(roles);
		return systemAdmin;
	}

	private void updateUserFromRequestDto(User user, UserRegistrationDto requestUser) {
		user.setEmail(requestUser.getEmail());
		user.setPassword(requestUser.getPassword());
		user.setName(requestUser.getName());
		user.setSurname(requestUser.getSurname());
		user.setLocation(requestUser.getLocation());
		user.setPhoneNumber(requestUser.getPhoneNumber());
		user.setLocation(locationRepository.save(requestUser.getLocation()));
	}

	public boolean verifyActivationCodeAndActivateUser(String activationCode) {

		User userWithActivationCode = userRepository.findByActivationCode(activationCode);

		if (userWithActivationCode == null) {
			return false;
		}

		userWithActivationCode.setActivationCode("");
		userWithActivationCode.setStatus(true);
		userRepository.save(userWithActivationCode);

		return true;
	}
}
