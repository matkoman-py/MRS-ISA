package pharmacyhub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pharmacyhub.domain.users.Role;

public interface RoleRepository extends JpaRepository<Role, String>{
	List<Role> findByName(String name);
}
