package pharmacyhub.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.enums.UserType;
import pharmacyhub.domain.users.Patient;
import pharmacyhub.domain.users.User;
import pharmacyhub.dto.UserRegistrationDto;
import pharmacyhub.repositories.LocationRepository;
import pharmacyhub.repositories.users.UserRepository;

@Service
public class RegistrationService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	private UserNotificationService userNotificationService;

	public User registerUser(UserRegistrationDto requestUser) throws Exception {

		User user = userRepository.findByEmail(requestUser.getEmail());
		
		if (user != null) 
		{
			throw new Exception("Email already taken!");
		}
		
		if (!requestUser.getPassword().equals(requestUser.getRepeatedPassword())) {
			throw new Exception("Passwords do not match");
		}
		
		String activationCode = generateActivationCode();
		
		if (requestUser.getType() == UserType.Patient) {
			Patient patient = userRepository.save(getPatientFromUserRegistrationDto(requestUser));
			patient.setActivationCode(activationCode);
			patient.setStatus(false);
			user = userRepository.save(patient);
		}
		
		userNotificationService.sendActivationCode(user.getEmail(), activationCode);

		return user;
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
	
	private String generateActivationCode() {
		char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 60; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		return sb.toString();
	}
}
