package pharmacyhub.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import pharmacyhub.domain.DermatologistAppointment;
import pharmacyhub.domain.users.Dermatologist;

public interface DermatologistAppointmentRepository  extends JpaRepository<DermatologistAppointment, String> {
	DermatologistAppointment findByDrugstoreId(String drugstoreId);
	@Transactional
	void deleteByDermatologist(Dermatologist dermatologist);
	List<DermatologistAppointment> findByDermatologistId(String dermatologistId);
	List<DermatologistAppointment> findByPatientId(String patientId);
	List<DermatologistAppointment> findByPatientIdAndDermatologistIdAndProcessedTrue(String patientId, String dermatologistId);
	List<DermatologistAppointment> findByPatientIdAndDrugstoreIdAndProcessedTrue(String patientId, String drugstoreId);
}
