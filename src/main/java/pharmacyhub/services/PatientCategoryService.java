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
	
	public List<PatientCategory> getAll(){
		return patientCategoryRepository.findAll();
	}
	
	public LoyaltyConfiguration getLoyaltyConfiguration(){
		return loyaltyConfigurationRepository.findById(1).orElse(null);
	}
	
	public boolean updatePatientCategoryFromAppointment(Patient patient, String type) {
		LoyaltyConfiguration loyaltyConfiguration = loyaltyConfigurationRepository.findById(1).orElse(null);
		int pointsGained = (type.equals("dermatologist") ? loyaltyConfiguration.getDermatologistPointsGained() : loyaltyConfiguration.getPharmacistPointsGained());
		return updatePatientCategory(patient, pointsGained);
	}
	
	
	public boolean updatePatientCategory(Patient patient, int pointsGained) {
		patient.setPoints(patient.getPoints() + pointsGained);
		
		List<PatientCategory> categories = patientCategoryRepository.findAllByOrderByRequieredPointsAsc();

		if (categories.isEmpty()) {
			patientRepository.save(patient);
			return false;
		}

		System.out.println(patient.getPoints());
		
		PatientCategory newCategory = findFittingPatientCategory(patient);
		patient.setCategory(newCategory);
		
		patientRepository.save(patient);
		
		return true;
	}
	
	private PatientCategory findFittingPatientCategory(Patient patient) {
		List<PatientCategory> categories = patientCategoryRepository.findAllByOrderByRequieredPointsAsc();
		
		PatientCategory temp = null;
		for (PatientCategory category : categories) {
			if(patient.getPoints() < category.getRequieredPoints()) {
				return temp;
			}
			temp = category;
		}
		return temp;
	}
	
	public double getPriceWithDiscount(Patient patient, double price) {
		if (patient.getCategory() == null) {
			return price;
		}
		double priceWithDiscount = price * (100-patient.getCategory().getDiscount()) / 100;
		return priceWithDiscount;
	}

	public PatientCategory updatePatientCategory(PatientCategory patientCategory) {
		return patientCategoryRepository.save(patientCategory);
	}

	public LoyaltyConfiguration updateLoyaltyConfiguration(LoyaltyConfiguration loyaltyConfiguration) {
		LoyaltyConfiguration loyaltyConfigurationToUpdate = loyaltyConfigurationRepository.findById(1).orElse(null);
		loyaltyConfigurationToUpdate.setDermatologistPointsGained(loyaltyConfiguration.getDermatologistPointsGained());
		loyaltyConfigurationToUpdate.setPharmacistPointsGained(loyaltyConfiguration.getPharmacistPointsGained());
		return loyaltyConfigurationRepository.save(loyaltyConfigurationToUpdate);
	}
}
