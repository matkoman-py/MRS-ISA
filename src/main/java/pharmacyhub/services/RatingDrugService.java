package pharmacyhub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.Drug;
import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.RatingDrug;
import pharmacyhub.domain.RatingDrugstore;
import pharmacyhub.domain.users.Patient;
import pharmacyhub.repositories.DrugRepository;
import pharmacyhub.repositories.RatingDrugRepository;
import pharmacyhub.repositories.users.PatientRepository;

@Service
public class RatingDrugService {
	
	@Autowired
	private RatingDrugRepository ratingDrugRepository;
	
	@Autowired
	private DrugRepository drugRepository;
	
	@Autowired
	private PatientRepository patientRepository;
	
	public List<RatingDrug> findAll()
	{
		return ratingDrugRepository.findAll();
	}
	
	public List<RatingDrug> saveRating(String drugId, String patientId, String rating) {
		Patient patient = patientRepository.findById(patientId).orElse(null);
		Drug drug = drugRepository.findById(drugId).orElse(null);
		int ratingg = Integer.parseInt(rating);
		
		RatingDrug rtd =  ratingDrugRepository.findByDrugAndPatient(patient,drug);
		if(rtd == null) {
			rtd = new RatingDrug(patient,drug,ratingg);
		}
		else {
			rtd.setRating(ratingg);
			ratingDrugRepository.save(rtd);
		}
		return null;
	}
}
