package pharmacyhub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.RatingDrugstore;
import pharmacyhub.domain.RatingPharmacist;
import pharmacyhub.domain.users.Patient;
import pharmacyhub.domain.users.Pharmacist;
import pharmacyhub.repositories.RatingPharmacistRepository;
import pharmacyhub.repositories.users.PatientRepository;
import pharmacyhub.repositories.users.PharmacistRepository;

@Service
public class RatingPharmacistService {
	
	@Autowired
	private PharmacistRepository pharmacistRepository;
	
	@Autowired
	private RatingPharmacistRepository ratingPharmacistRepository;
	
	@Autowired
	private  PatientRepository patientRepository;
	
	public List<RatingPharmacist> findAll()
	{
		return ratingPharmacistRepository.findAll();
	}
	
	public List<RatingPharmacist> saveRating(String pharmacistId, String patientId, String rating) {
		Patient patient = patientRepository.findById(patientId).orElse(null);
		Pharmacist pharmacist = pharmacistRepository.findById(pharmacistId).orElse(null);
		int ratingg = Integer.parseInt(rating);
		
		RatingPharmacist rtd = ratingPharmacistRepository.findByPharmacistAndPatient(patient,pharmacist);
		if(rtd == null) {
			rtd = new RatingPharmacist(patient,pharmacist,ratingg);
		}
		else {
			rtd.setRating(ratingg);
			ratingPharmacistRepository.save(rtd);
		}
		return null;
	}
}
