package pharmacyhub.services;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.users.DrugstoreAdmin;
import pharmacyhub.domain.users.Patient;
import pharmacyhub.domain.users.Supplier;
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
	private UserNotificationService userNotificationService;

	public boolean registerUser(UserRegistrationDto requestUser) throws Exception {

		User user = userRepository.findByEmail(requestUser.getEmail());
		
		if (user != null) 
		{
			throw new Exception("Email already taken!");
		}
		
		switch(requestUser.getType()) {
		  case Patient:
			  savePatient(requestUser);
			  break;
		  case Supplier:
			  saveSupplier(requestUser);
			  break;
		  case DrugstoreAdmin:
			  saveDrugstoreAdmin(requestUser);
			  break;
		  default:
		}
		
		return true;

	}
	
	private void saveDrugstoreAdmin(UserRegistrationDto requestUser) throws Exception {
		DrugstoreAdmin drugstoreAdmin = userRepository.save(getDrugstoreAdminFromUserRegistrationDto(requestUser));
		userNotificationService.sendEmployeeInitialPassword(drugstoreAdmin.getEmail(), drugstoreAdmin.getPassword());
	}
	
	private DrugstoreAdmin getDrugstoreAdminFromUserRegistrationDto(UserRegistrationDto requestUser) throws Exception {
		DrugstoreAdmin drugstoreAdmin = new DrugstoreAdmin();
		drugstoreAdmin.setEmail(requestUser.getEmail());
		drugstoreAdmin.setPassword(RadnomGeneratorUtil.generateEmployeePassword());
		drugstoreAdmin.setName(requestUser.getName());
		drugstoreAdmin.setSurname(requestUser.getSurname());
		drugstoreAdmin.setLocation(requestUser.getLocation());
		drugstoreAdmin.setPhoneNumber(requestUser.getPhoneNumber());
		drugstoreAdmin.setLocation(locationRepository.save(requestUser.getLocation()));
		Drugstore drugstore = drugstoreRepository.findById(requestUser.getDrugstoreId()).orElse(null);
		if (drugstore == null) {
			throw new Exception("Drugstore with that id doesn't exist.");
		}
		drugstoreAdmin.setDrugstore(drugstore);
		
		return drugstoreAdmin;
	}

	private void saveSupplier(UserRegistrationDto requestUser) throws MessagingException {
		Supplier supplier = userRepository.save(getSupplierFromUserRegistrationDto(requestUser));
		userNotificationService.sendEmployeeInitialPassword(supplier.getEmail(), supplier.getPassword());
	}

	private Supplier getSupplierFromUserRegistrationDto(UserRegistrationDto requestUser) {
		Supplier supplier = new Supplier();
		supplier.setEmail(requestUser.getEmail());
		supplier.setPassword(RadnomGeneratorUtil.generateEmployeePassword());
		supplier.setName(requestUser.getName());
		supplier.setSurname(requestUser.getSurname());
		supplier.setLocation(requestUser.getLocation());
		supplier.setPhoneNumber(requestUser.getPhoneNumber());
		supplier.setLocation(locationRepository.save(requestUser.getLocation()));

		return supplier;
	}

	private void savePatient(UserRegistrationDto requestUser) throws Exception {
		String activationCode = RadnomGeneratorUtil.generateActivationCode();
		
		Patient patient = userRepository.save(getPatientFromUserRegistrationDto(requestUser));
		patient.setActivationCode(activationCode);
		patient.setStatus(false);
		
		if (!requestUser.getPassword().equals(requestUser.getRepeatedPassword())) {
			throw new Exception("Passwords do not match");
		}
		
		userNotificationService.sendActivationCode(patient.getEmail(), activationCode);
	}

	private Patient getPatientFromUserRegistrationDto(UserRegistrationDto requestUser) {
		Patient patient = new Patient();
		patient.setEmail(requestUser.getEmail());
		patient.setPassword(requestUser.getPassword());
		patient.setName(requestUser.getName());
		patient.setSurname(requestUser.getSurname());
		patient.setLocation(requestUser.getLocation());
		patient.setPhoneNumber(requestUser.getPhoneNumber());
		patient.setLocation(locationRepository.save(requestUser.getLocation()));

		return patient;
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
