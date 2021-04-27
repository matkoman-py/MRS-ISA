package pharmacyhub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pharmacyhub.domain.DrugReservation;

public interface DrugReservationRepository extends JpaRepository<DrugReservation, String>{

}
