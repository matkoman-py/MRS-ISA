package pharmacyhub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pharmacyhub.domain.users.DrugstoreAdmin;
import pharmacyhub.domain.users.SystemAdmin;
import pharmacyhub.domain.users.User;
import pharmacyhub.dto.FirstPasswordChangeDto;
import pharmacyhub.repositories.users.DrugstoreAdminRepository;
import pharmacyhub.repositories.users.UserRepository;

@Service
@Transactional
public class UserSerivce {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	DrugstoreAdminRepository drugstoreAdminRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<User> getSuppliersAndAdmins() {
		return userRepository.findSuppliersAndAdmins();
	}

	public DrugstoreAdmin getDrugstoreAdmin(String drugstoreAdminId) {
		return (DrugstoreAdmin) userRepository.findById(drugstoreAdminId).orElse(null);
	}
	
	public SystemAdmin getSystemAdmin(String drugstoreAdminId) {
		return (SystemAdmin) userRepository.findById(drugstoreAdminId).orElse(null);
	}
	
	public Boolean drugstoreAdminUpdate(DrugstoreAdmin drugstoreAdmin) throws Exception {
			DrugstoreAdmin admin = (DrugstoreAdmin) userRepository.findById(drugstoreAdmin.getId()).orElse(null);
			if (admin.equals(null)) {
				throw new Exception("This drugstoreAdmin does not exist!");
			}
			if(drugstoreAdmin.getEmail() != null) admin.setEmail(drugstoreAdmin.getEmail());
			//if(admin.getLocation() != null) admin.setLocation(drugstoreAdmin.getLocation());
			if(drugstoreAdmin.getName() != null) admin.setName(drugstoreAdmin.getName());
			if(drugstoreAdmin.getPassword() != null) admin.setPassword(drugstoreAdmin.getPassword());
			if(drugstoreAdmin.getPhoneNumber() != null) admin.setPhoneNumber(drugstoreAdmin.getPhoneNumber());
			if(drugstoreAdmin.getSurname() != null) admin.setSurname(drugstoreAdmin.getSurname());

			userRepository.save(admin);
		return true;
	}
	
	public Boolean systemAdminUpdate(SystemAdmin drugstoreAdmin) throws Exception {
		SystemAdmin admin = (SystemAdmin) userRepository.findById(drugstoreAdmin.getId()).orElse(null);
		if (admin.equals(null)) {
			throw new Exception("This drugstoreAdmin does not exist!");
		}
		if(drugstoreAdmin.getEmail() != null) admin.setEmail(drugstoreAdmin.getEmail());
		//if(admin.getLocation() != null) admin.setLocation(drugstoreAdmin.getLocation());
		if(drugstoreAdmin.getName() != null) admin.setName(drugstoreAdmin.getName());
		if(drugstoreAdmin.getPassword() != null) admin.setPassword(drugstoreAdmin.getPassword());
		if(drugstoreAdmin.getPhoneNumber() != null) admin.setPhoneNumber(drugstoreAdmin.getPhoneNumber());
		if(drugstoreAdmin.getSurname() != null) admin.setSurname(drugstoreAdmin.getSurname());

		userRepository.save(admin);
	return true;
}
	
	public boolean validatePassword(String drugstoreAdminId, String passwordInput) {
		DrugstoreAdmin admin = (DrugstoreAdmin) userRepository.findById(drugstoreAdminId).orElse(null);
		return passwordEncoder.matches(passwordInput, admin.getPassword());
	}
	
	public boolean validatePasswordsystemAdmin(String drugstoreAdminId, String passwordInput) {
		SystemAdmin admin = (SystemAdmin) userRepository.findById(drugstoreAdminId).orElse(null);
		return passwordEncoder.matches(passwordInput, admin.getPassword());
	}
	
	public boolean systemAdminPasswordUpdate(SystemAdmin drugstoreAdmin) throws Exception {
		SystemAdmin admin = (SystemAdmin) userRepository.findById(drugstoreAdmin.getId()).orElse(null);
		if (admin == null) {
			throw new Exception("This drugstore admin does not exist!");
		}		
		admin.setPassword(passwordEncoder.encode(drugstoreAdmin.getPassword()));
		userRepository.save(admin);
		return true;
	}
	
	public boolean drugstoreAdminPasswordUpdate(DrugstoreAdmin drugstoreAdmin) throws Exception {
		DrugstoreAdmin admin = (DrugstoreAdmin) userRepository.findById(drugstoreAdmin.getId()).orElse(null);
		if (admin == null) {
			throw new Exception("This drugstore admin does not exist!");
		}		
		admin.setPassword(passwordEncoder.encode(drugstoreAdmin.getPassword()));
		userRepository.save(admin);
		return true;
	}
	
	public boolean firstLoginPasswordChange(FirstPasswordChangeDto firstPasswordChangeDto) {
		User user = userRepository.findById(firstPasswordChangeDto.getId()).orElse(null);
		user.setPassword(passwordEncoder.encode(firstPasswordChangeDto.getPassword()));
		user.setFirstLogin(false);
		userRepository.save(user);
		return true;
	}
	@Transactional
	public boolean deleteDrugstoreAdmin(String drugstoreAdminId) {
		drugstoreAdminRepository.deleteById(drugstoreAdminId);
		return true;
	}
	
}
