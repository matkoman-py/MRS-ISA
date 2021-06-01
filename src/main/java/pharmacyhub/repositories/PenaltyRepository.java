package pharmacyhub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pharmacyhub.domain.Penalty;

public interface PenaltyRepository extends JpaRepository<Penalty, String>{

	Penalty findByReservationId(String reservationId);

}
