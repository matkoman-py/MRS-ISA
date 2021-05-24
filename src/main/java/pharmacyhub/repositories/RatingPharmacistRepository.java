package pharmacyhub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pharmacyhub.domain.RatingPharmacist;
import pharmacyhub.domain.users.Patient;
import pharmacyhub.domain.users.Pharmacist;

public interface RatingPharmacistRepository extends JpaRepository<RatingPharmacist, String>{
	RatingPharmacist findByPharmacistAndPatient(Patient patient, Pharmacist pharmacist);
	List<RatingPharmacist> findByPharmacist(Pharmacist pharmacist);
	RatingPharmacist findByPharmacistAndPatient(Pharmacist pharmacist,Patient patient);

}
