package pharmacyhub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.Subscription;
import pharmacyhub.domain.users.Patient;

public interface SubscriptionRepository extends JpaRepository<Subscription, String>{
	
	Subscription findByPatientAndDrugstore(Patient patient, Drugstore drugstore);
	List<Subscription> findByDrugstore(Drugstore drugstore);
	@Transactional
	void deleteByPatientAndDrugstore(Patient patient, Drugstore drugstore);
	List<Subscription> findByPatient(Patient patinet);

}
