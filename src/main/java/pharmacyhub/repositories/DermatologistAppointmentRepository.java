package pharmacyhub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pharmacyhub.domain.DermatologistAppointment;
import pharmacyhub.domain.Drug;

public interface DermatologistAppointmentRepository  extends JpaRepository<DermatologistAppointment, String> {
	DermatologistAppointment findByDrugstoreId(String drugstoreId);
}
