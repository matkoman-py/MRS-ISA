package pharmacyhub.repositories.users;

import javax.transaction.Transactional;

import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.users.DrugstoreAdmin;

@Transactional
public interface DrugstoreAdminRepository extends AbstractUserRepository<DrugstoreAdmin>{
	void deleteByDrugstore(Drugstore drugstore);
}
