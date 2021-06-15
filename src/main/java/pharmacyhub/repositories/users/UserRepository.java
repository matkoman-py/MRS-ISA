package pharmacyhub.repositories.users;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;

import pharmacyhub.domain.users.User;

@Transactional
public interface UserRepository extends AbstractUserRepository<User> {

	@Query("from User where type like 'Supplier%' or "
			+ "type like 'DrugstoreAdmin%' or "
			+ "type like 'SystemAdmin%'  or "
			+ "type like 'Dermatologist%'")
	List<User> findSuppliersAndAdmins();
}
