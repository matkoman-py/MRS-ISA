package pharmacyhub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pharmacyhub.domain.DermatologistEmployment;
import pharmacyhub.domain.Drugstore;

public interface DermatologistEmploymentRepository extends JpaRepository<DermatologistEmployment, String> {
	List<DermatologistEmployment> findByDrugstore(Drugstore drugstore);
}
