package pharmacyhub.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import pharmacyhub.domain.DermatologistAppointment;
import pharmacyhub.domain.users.Dermatologist;

public interface DermatologistAppointmentRepository  extends JpaRepository<DermatologistAppointment, String> {
	DermatologistAppointment findByDrugstoreId(String drugstoreId);
	@Transactional
	void deleteByDermatologist(Dermatologist dermatologist);
}
