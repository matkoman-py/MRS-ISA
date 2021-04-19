package pharmacyhub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.users.Role;
import pharmacyhub.repositories.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	public List<Role> findByName(String name){
		return roleRepository.findByName(name);
	}
}
