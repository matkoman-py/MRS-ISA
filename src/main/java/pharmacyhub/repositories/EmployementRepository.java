package pharmacyhub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pharmacyhub.domain.Employement;

public interface EmployementRepository extends JpaRepository<Employement, String>{
	Employement findByDermatologistIdAndDrugstoreId(String dermatologistId, String drugstoreId);
}
