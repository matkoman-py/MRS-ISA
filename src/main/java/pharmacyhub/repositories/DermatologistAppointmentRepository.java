package pharmacyhub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pharmacyhub.domain.DermatologistAppointment;
import pharmacyhub.domain.Drug;
import pharmacyhub.domain.PharmacistAppointment;

public interface DermatologistAppointmentRepository  extends JpaRepository<DermatologistAppointment, String> {
	DermatologistAppointment findByDrugstoreId(String drugstoreId);
	List<DermatologistAppointment> findByDermatologistId(String dermatologistId);
	List<DermatologistAppointment> findByPatientId(String patientId);
}
