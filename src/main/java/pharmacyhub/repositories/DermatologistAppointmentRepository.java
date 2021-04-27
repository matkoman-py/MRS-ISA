package pharmacyhub.repositories;

import javax.transaction.Transactional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pharmacyhub.domain.DermatologistAppointment;
import pharmacyhub.domain.users.Dermatologist;

import pharmacyhub.domain.Drug;

public interface DermatologistAppointmentRepository  extends JpaRepository<DermatologistAppointment, String> {
	DermatologistAppointment findByDrugstoreId(String drugstoreId);
	@Transactional
	void deleteByDermatologist(Dermatologist dermatologist);
	List<DermatologistAppointment> findByDermatologistId(String dermatologistId);
	List<DermatologistAppointment> findByPatientId(String patientId);
}
