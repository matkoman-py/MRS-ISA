package pharmacyhub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.RatingDrugstore;
import pharmacyhub.domain.users.Patient;

public interface RatingDrugstoreRepository extends JpaRepository<RatingDrugstore, String>{
	RatingDrugstore findByDrugstoreAndPatient(Drugstore drugstore,Patient patient);
}
