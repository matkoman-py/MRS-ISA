package pharmacyhub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pharmacyhub.domain.RatingDermatologist;
import pharmacyhub.domain.users.Dermatologist;
import pharmacyhub.domain.users.Patient;

public interface RatingDermatologistRepository extends JpaRepository<RatingDermatologist, String> {
	RatingDermatologist findByDermatologistAndPatient(Patient patient, Dermatologist dermatologist);
	List<RatingDermatologist> findByDermatologist(Dermatologist dermatologist);
}
