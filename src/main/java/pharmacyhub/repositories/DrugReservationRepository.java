package pharmacyhub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import pharmacyhub.domain.DrugReservation;
import pharmacyhub.domain.users.Patient;

public interface DrugReservationRepository extends JpaRepository<DrugReservation, String>{

	List<DrugReservation> findByPatient(Patient patient);

}
