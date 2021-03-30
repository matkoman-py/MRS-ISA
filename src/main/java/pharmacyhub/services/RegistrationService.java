package pharmacyhub.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.enums.UserType;
import pharmacyhub.domain.users.Patient;
import pharmacyhub.domain.users.User;
import pharmacyhub.dto.UserRegistrationDto;
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
	private UserNotificationService userNotificationService;

	public boolean registerUser(UserRegistrationDto requestUser) throws Exception {

		User user = userRepository.findByEmail(requestUser.getEmail());
		
		if (user != null) 
		{
			throw new Exception("Email already taken!");
		}
		
		
		if (requestUser.getType() == UserType.Patient) {
			savePatient(requestUser);
		}
		
		return true;

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
