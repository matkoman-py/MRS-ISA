package pharmacyhub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pharmacyhub.domain.RatingDermatologist;
import pharmacyhub.domain.users.Dermatologist;
import pharmacyhub.domain.users.Patient;

public interface RatingDermatologistRepository extends JpaRepository<RatingDermatologist, String> {
	RatingDermatologist findByDermatologistAndPatient(Patient patient, Dermatologist dermatologist);

}
