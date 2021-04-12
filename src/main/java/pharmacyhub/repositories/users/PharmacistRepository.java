package pharmacyhub.repositories.users;

import javax.transaction.Transactional;

import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.users.Pharmacist;

@Transactional
public interface PharmacistRepository extends EmployeeRepository<Pharmacist> {
	void deleteByDrugstore(Drugstore drugstore);
}
