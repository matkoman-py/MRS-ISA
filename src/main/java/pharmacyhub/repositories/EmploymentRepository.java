package pharmacyhub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pharmacyhub.domain.Employment;

public interface EmploymentRepository extends JpaRepository<Employment, String>{
	Employment findByDermatologistIdAndDrugstoreId(String dermatologistId, String drugstoreId);
	List<Employment> findByDrugstoreId(String drugstoreId);
	List<Employment> findByDermatologistId(String dermatologistId);

}
