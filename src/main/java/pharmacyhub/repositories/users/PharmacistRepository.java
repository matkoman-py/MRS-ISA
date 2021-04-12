package pharmacyhub.repositories.users;

import java.util.List;

import javax.transaction.Transactional;

import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.users.Pharmacist;

@Transactional
public interface PharmacistRepository extends EmployeeRepository<Pharmacist> {	
	List<Pharmacist> findByDrugstore(Drugstore drugstore);
	void deleteByDrugstore(Drugstore drugstore);
}
