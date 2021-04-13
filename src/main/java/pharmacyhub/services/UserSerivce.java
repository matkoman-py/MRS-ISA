package pharmacyhub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.users.User;
import pharmacyhub.repositories.users.UserRepository;

@Service
public class UserSerivce {

	@Autowired
	UserRepository userRepository;

	public List<User> getSuppliersAndAdmins() {
		// TODO Auto-generated method stub
		return userRepository.findSuppliersAndAdmins();
	}
	
	
}
