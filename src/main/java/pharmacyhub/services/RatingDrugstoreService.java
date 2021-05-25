package pharmacyhub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.RatingDrugstore;
import pharmacyhub.domain.users.Patient;
import pharmacyhub.repositories.DrugstoreRepository;
import pharmacyhub.repositories.RatingDrugstoreRepository;
import pharmacyhub.repositories.users.PatientRepository;

@Service
public class RatingDrugstoreService {
	
	@Autowired
	private DrugstoreRepository drugstoreRepository;
	
	@Autowired
	private RatingDrugstoreRepository ratingDrugstoreRepository;
	
	@Autowired
	private PatientRepository patientRepository;
	
	public List<RatingDrugstore> findAll()
	{
		return ratingDrugstoreRepository.findAll();
	}

	public List<RatingDrugstore> saveRating(String drugstoreId, String patientId, String rating) {
		Patient patient = patientRepository.findById(patientId).orElse(null);
		Drugstore drugstore = drugstoreRepository.findById(drugstoreId).orElse(null);
		int ratingg = Integer.parseInt(rating);
		
		RatingDrugstore rtd =  ratingDrugstoreRepository.findByDrugstoreAndPatient(drugstore,patient);
		if(rtd == null) {
			rtd = new RatingDrugstore(drugstore,patient,ratingg);
		}
		else {
			rtd.setRating(ratingg);
		}
		ratingDrugstoreRepository.save(rtd);
		return null;
	}
}
