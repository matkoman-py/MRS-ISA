package pharmacyhub.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import pharmacyhub.domain.Drug;
import pharmacyhub.domain.DrugReservation;
import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.users.Patient;

public interface DrugReservationRepository extends JpaRepository<DrugReservation, String>{

	List<DrugReservation> findByPatient(Patient patient, Pageable pageable);
	List<DrugReservation> findByPatient(Patient patient);
	List<DrugReservation> findByDrugAndDrugstore(Drug drug, Drugstore drugstore);
	List<DrugReservation> findByDrugstoreAndPatient(Drugstore drugstore, Patient patient);
	List<DrugReservation> findByDrugstoreIdAndPatientIdAndIssuedTrue(String drugstoreId, String patientId);
	List<DrugReservation> findByDrugstoreAndIssuedTrue(Drugstore drugstore);
	DrugReservation findByConfirmationCode(String confirmationCode);
	boolean existsByDrugstoreIdAndDrugIdAndAmountGreaterThanEqual(String drugstoreId, String drugId, int amount);

}