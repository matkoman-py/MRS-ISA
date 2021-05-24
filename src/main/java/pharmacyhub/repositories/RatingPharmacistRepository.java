package pharmacyhub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pharmacyhub.domain.RatingDrugstore;
import pharmacyhub.domain.RatingPharmacist;
import pharmacyhub.domain.users.Patient;
import pharmacyhub.domain.users.Pharmacist;

public interface RatingPharmacistRepository extends JpaRepository<RatingPharmacist, String>{
	RatingPharmacist findByPharmacistAndPatient(Pharmacist pharmacist,Patient patient);

}
