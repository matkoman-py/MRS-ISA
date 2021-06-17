package pharmacyhub.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import pharmacyhub.domain.Drug;
import pharmacyhub.domain.DrugReservation;
import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.enums.DrugReservationStatus;
import pharmacyhub.domain.users.Patient;

public interface DrugReservationRepository extends JpaRepository<DrugReservation, String>{

	List<DrugReservation> findByPatient(Patient patient, Pageable pageable);
	List<DrugReservation> findByPatient(Patient patient);
	
	//@Query("select dr from DrugReservation dr where dr.patient.id = ?1")
	List<DrugReservation> findByPatientId(String patientId);
	
	@Transactional
	void deleteById(String id);
	
	List<DrugReservation> findByDrugAndDrugstore(Drug drug, Drugstore drugstore);
	List<DrugReservation> findByDrugstoreAndPatient(Drugstore drugstore, Patient patient);
	List<DrugReservation> findByDrugstoreIdAndPatientIdAndStatus(String drugstoreId, String patientId, DrugReservationStatus status);
	List<DrugReservation> findByDrugstoreAndStatus(Drugstore drugstore, DrugReservationStatus status);
	DrugReservation findByConfirmationCode(String confirmationCode);
	boolean existsByDrugstoreIdAndDrugIdAndAmountGreaterThanEqual(String drugstoreId, String drugId, int amount);
	List<DrugReservation> findByStatus(DrugReservationStatus status);
}