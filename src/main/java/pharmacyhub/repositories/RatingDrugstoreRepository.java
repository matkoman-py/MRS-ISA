package pharmacyhub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.RatingDrugstore;
import pharmacyhub.domain.users.Patient;

public interface RatingDrugstoreRepository extends JpaRepository<RatingDrugstore, String>{
	RatingDrugstore findByDrugstoreAndPatient(Patient patient, Drugstore drugstore);
	List<RatingDrugstore> findByDrugstore(Drugstore drugstore);
	RatingDrugstore findByDrugstoreAndPatient(Drugstore drugstore,Patient patient);
}
