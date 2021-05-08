package pharmacyhub.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.Subscription;
import pharmacyhub.domain.users.Patient;
import pharmacyhub.dto.subscriptions.SubscriptionDetailsDto;
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
		Subscription subscription = subscriptionRepository.findByPatientAndDrugstore(
				patientRepository.findById(patientId).orElse(null),
				drugstoreRepository.findById(drugstoreId).orElse(null));
		return subscription != null;
	}

	public boolean subscribe(String patientId, String drugstoreId) {
		subscriptionRepository.save(new Subscription(patientRepository.findById(patientId).orElse(null),
				drugstoreRepository.findById(drugstoreId).orElse(null)));
		return true;
	}

	public boolean unsubscribe(String patientId, String drugstoreId) {
		subscriptionRepository.deleteByPatientAndDrugstore(patientRepository.findById(patientId).orElse(null),
				drugstoreRepository.findById(drugstoreId).orElse(null));
		return false;
	}

	private SubscriptionDetailsDto subsriptionToDetailsDto(Subscription subscription) {

		SubscriptionDetailsDto subscriptionDeatilsDto = new SubscriptionDetailsDto();
		subscriptionDeatilsDto.setPatientId(subscription.getPatient().getId());
		subscriptionDeatilsDto.setDrugstoreId(subscription.getDrugstore().getId());
		subscriptionDeatilsDto.setLocation(subscription.getDrugstore().getLocation());
		subscriptionDeatilsDto.setName(subscription.getDrugstore().getName());
		subscriptionDeatilsDto.setWorkingHoursFrom(subscription.getDrugstore().getWorkingHoursFrom());
		subscriptionDeatilsDto.setWorkingHoursTo(subscription.getDrugstore().getWorkingHoursTo());

		return subscriptionDeatilsDto;
	}

	public List<SubscriptionDetailsDto> readUserSpecificSubs(String patientId) throws Exception {
		Patient patient = patientRepository.findById(patientId).orElse(null);
		if (patient == null) {
			throw new Exception("Patient with given id doesn't exist");
		}
		return subscriptionRepository.findByPatient(patient)
				.stream().map(sub -> subsriptionToDetailsDto(sub))
				.collect(Collectors.toList());
	}

}
