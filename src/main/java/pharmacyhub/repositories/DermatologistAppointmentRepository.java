package pharmacyhub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pharmacyhub.domain.DermatologistAppointment;

public interface DermatologistAppointmentRepository  extends JpaRepository<DermatologistAppointment, String> {

}
