package pharmacyhub.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.Subscription;
import pharmacyhub.domain.users.Patient;
import pharmacyhub.repositories.DrugstoreRepository;
import pharmacyhub.repositories.SubscriptionRepository;
import pharmacyhub.repositories.users.PatientRepository;

@Service
public class SubscriptionService {
	
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private DrugstoreRepository drugstoreRepository;

	public boolean checkSubscription(String patientId, String drugstoreId) {
		Subscription subscription = subscriptionRepository.findByPatientAndDrugstore(patientRepository.findById(patientId).orElse(null), drugstoreRepository.findById(drugstoreId).orElse(null));
		return subscription != null;
	}

	public boolean subscribe(String patientId, String drugstoreId) {
		subscriptionRepository.save(new Subscription(patientRepository.findById(patientId).orElse(null), drugstoreRepository.findById(drugstoreId).orElse(null)));
		return true;
	}

	public boolean unsubscribe(String patientId, String drugstoreId) {
		subscriptionRepository.deleteByPatientAndDrugstore(patientRepository.findById(patientId).orElse(null), drugstoreRepository.findById(drugstoreId).orElse(null));
		return false;
	}

}
