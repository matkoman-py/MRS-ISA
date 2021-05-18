package pharmacyhub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pharmacyhub.domain.Drug;
import pharmacyhub.domain.RatingDrug;
import pharmacyhub.domain.users.Patient;

public interface RatingDrugRepository extends JpaRepository<RatingDrug, String>{
	RatingDrug findByDrugAndPatient(Patient patient, Drug drug);

}
