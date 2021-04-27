package pharmacyhub.repositories.users;

import java.util.List;

import javax.transaction.Transactional;

import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.users.Pharmacist;

public interface PharmacistRepository extends EmployeeRepository<Pharmacist> {	
	List<Pharmacist> findByDrugstore(Drugstore drugstore);
	@Transactional
	void deleteByDrugstore(Drugstore drugstore);
	@Transactional
	void deleteByEmail(String email);
}
