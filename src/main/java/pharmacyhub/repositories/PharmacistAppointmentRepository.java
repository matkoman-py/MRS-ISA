package pharmacyhub.repositories;

import java.util.List;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.PharmacistAppointment;
import pharmacyhub.domain.users.Pharmacist;

public interface PharmacistAppointmentRepository extends JpaRepository<PharmacistAppointment, String> {
	
	@Transactional
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	List<PharmacistAppointment> findByPharmacistId(String pharmacistId);

//	1 -> findByPatientId = READ
//	2 -> findByPatientId = READ
//	1/2 -> WRITE
//	2 -> 1
//  pharamcist_id, patient_id, date, time
//	1 -> WRITE -> x -> 2
//	
	
	@Transactional
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	List<PharmacistAppointment> findByPatientId(String patientId);
	
	
	List<PharmacistAppointment> findByPatientId(String patientId, Pageable pageable);
	List<PharmacistAppointment> findByPharmacistAndProcessed(Pharmacist pharmacist, boolean processed);
	List<PharmacistAppointment> findByPharmacistIdAndProcessedTrue(String pharmacistId);
	List<PharmacistAppointment> findByPharmacistIdAndProcessedTrue(String pharmacistId, Pageable pageable);
	@Transactional
	void deleteByPharmacist(Pharmacist pharmacist);
	List<PharmacistAppointment> findByPatientIdAndPharmacistIdAndProcessedTrue(String patientId, String pharmacistId);
	List<PharmacistAppointment> findByPatientIdAndPharmacistDrugstoreIdAndProcessedTrue(String patientId, String drugstoreId);
	List<PharmacistAppointment> findByPharmacistIdAndProcessedFalseAndPatientNotNull(String pharmacistId, Pageable pageable);
	List<PharmacistAppointment> findByPharmacistIdAndProcessedFalseAndPatientNotNull(String pharmacistId);
	List<PharmacistAppointment> findByPharmacistDrugstoreAndProcessedTrue(Drugstore drugstore);
	List<PharmacistAppointment> findByPharmacistEmailAndProcessedFalse(String pharmacistEmail);
}
