package pharmacyhub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pharmacyhub.domain.Drug;
import pharmacyhub.domain.RatingDrug;
import pharmacyhub.domain.users.Patient;

public interface RatingDrugRepository extends JpaRepository<RatingDrug, String>{
	RatingDrug findByDrugAndPatient(Patient patient, Drug drug);
	List<RatingDrug> findByDrug(Drug drug);
}
