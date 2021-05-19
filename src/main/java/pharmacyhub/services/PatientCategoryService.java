package pharmacyhub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.LoyaltyConfiguration;
import pharmacyhub.domain.PatientCategory;
import pharmacyhub.domain.users.Patient;
import pharmacyhub.repositories.LoyaltyConfigurationRepository;
import pharmacyhub.repositories.PatientCategoryRepository;
import pharmacyhub.repositories.users.PatientRepository;

@Service
public class PatientCategoryService {
	
	@Autowired
	private PatientCategoryRepository patientCategoryRepository;
	

	@Autowired
	private PatientRepository patientRepository;
	

	@Autowired
	private LoyaltyConfigurationRepository loyaltyConfigurationRepository;
	
	
	public boolean updatePatientCategoryFromAppointment(Patient patient, String type) {
		LoyaltyConfiguration loyaltyConfiguration = loyaltyConfigurationRepository.findById(1).orElse(null);
		int pointsGained = (type.equals("dermatologist") ? loyaltyConfiguration.getDermatologistPointsGained() : loyaltyConfiguration.getPharmacistPointsGained());
		return updatePatientCategory(patient, pointsGained);
	}
	
	
	public boolean updatePatientCategory(Patient patient, int pointsGained) {
		patient.setPoints(patient.getPoints() + pointsGained);
		
		List<PatientCategory> categories = patientCategoryRepository.findByRequieredPointsGreaterThanOrderByRequieredPointsAsc(patient.getPoints() + pointsGained);
				
		System.out.println(categories.size());

		if (categories.isEmpty()) {
			patientRepository.save(patient);
			return false;
		}

		System.out.println(patient.getPoints());
		
		if (categories.get(0).getRequieredPoints() > patient.getPoints() + pointsGained) {
			patientRepository.save(patient);
			return false;
		}
		
		patient.setCategory(categories.get(0));
		patientRepository.save(patient);
		
		return true;
	}
}
