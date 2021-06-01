package pharmacyhub.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import pharmacyhub.domain.DermatologistAppointment;
import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.users.Dermatologist;

public interface DermatologistAppointmentRepository  extends JpaRepository<DermatologistAppointment, String> {
	DermatologistAppointment findByDrugstoreId(String drugstoreId);
	List<DermatologistAppointment> findByDermatologistAndProcessed(Dermatologist dermatologist, boolean processed);
	@Transactional
	void deleteByDermatologist(Dermatologist dermatologist);
	List<DermatologistAppointment> findByDermatologistId(String dermatologistId);
	List<DermatologistAppointment> findByDermatologistIdAndProcessedTrue(String dermatologistId);
	List<DermatologistAppointment> findByDermatologistIdAndProcessedTrue(String dermatologistId, Pageable pageable);
	List<DermatologistAppointment> findByPatientId(String patientId);
	List<DermatologistAppointment> findByPatientIdAndDermatologistIdAndProcessedTrue(String patientId, String dermatologistId);
	List<DermatologistAppointment> findByPatientIdAndDrugstoreIdAndProcessedTrue(String patientId, String drugstoreId);
	List<DermatologistAppointment> findByPatientId(String patientId, Pageable pageable);
	List<DermatologistAppointment> findByDermatologistIdAndProcessedFalseAndPatientNotNull(String dermatologistId, Pageable pageable);
	List<DermatologistAppointment> findByDermatologistIdAndProcessedFalseAndPatientNotNull(String dermatologistId);
	List<DermatologistAppointment> findByDrugstoreAndProcessedTrue(Drugstore drugstore);
}
