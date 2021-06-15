package pharmacyhub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.Employment;
import pharmacyhub.domain.users.Dermatologist;

public interface EmploymentRepository extends JpaRepository<Employment, String>{
	Employment findByDermatologistIdAndDrugstoreId(String dermatologistId, String drugstoreId);
	List<Employment> findByDrugstoreId(String drugstoreId);
	List<Employment> findByDermatologistId(String dermatologistId);
	@Transactional
	void deleteByDermatologistAndDrugstore(Dermatologist dermatologist, Drugstore drugstore);
	@Transactional
	void deleteByDermatologistEmail(String dermatologistEmail);
}
