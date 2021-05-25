package pharmacyhub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.users.Dermatologist;
import pharmacyhub.domain.users.DrugstoreAdmin;
import pharmacyhub.domain.users.User;
import pharmacyhub.repositories.users.UserRepository;

@Service
public class UserSerivce {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<User> getSuppliersAndAdmins() {
		return userRepository.findSuppliersAndAdmins();
	}

	public DrugstoreAdmin getDrugstoreAdmin(String drugstoreAdminId) {
		return (DrugstoreAdmin) userRepository.findById(drugstoreAdminId).orElse(null);
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

	public boolean validatePassword(String drugstoreAdminId, String passwordInput) {
		DrugstoreAdmin admin = (DrugstoreAdmin) userRepository.findById(drugstoreAdminId).orElse(null);
		return passwordEncoder.matches(passwordInput, admin.getPassword());
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
	
}
