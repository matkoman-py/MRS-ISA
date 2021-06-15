package pharmacyhub.repositories.users;

import org.springframework.transaction.annotation.Transactional;

import pharmacyhub.domain.users.Supplier;

public interface SupplierRepository extends AbstractUserRepository<Supplier>{

	@Transactional
	void deleteById(String id);
}
