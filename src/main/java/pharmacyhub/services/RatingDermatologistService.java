package pharmacyhub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pharmacyhub.domain.RatingDermatologist;
import pharmacyhub.domain.RatingPharmacist;
import pharmacyhub.domain.users.Dermatologist;
import pharmacyhub.domain.users.Patient;
import pharmacyhub.domain.users.Pharmacist;
import pharmacyhub.repositories.RatingDermatologistRepository;
import pharmacyhub.repositories.users.DermatologistRepository;
import pharmacyhub.repositories.users.PatientRepository;

@Service
public class RatingDermatologistService {
	
	@Autowired
	private DermatologistRepository dermatologistRepository;
	
	@Autowired
	private RatingDermatologistRepository ratingDermatologistRepository;
	
	@Autowired
	private  PatientRepository patientRepository;
	
	public List<RatingDermatologist> findAll()
	{
		return ratingDermatologistRepository.findAll();
	}
	
	public List<RatingDermatologist> saveRating(String dermatologistId, String patientId, String rating) {
		Patient patient = patientRepository.findById(patientId).orElse(null);
		Dermatologist dermatologist = dermatologistRepository.findById(dermatologistId).orElse(null);
		int ratingg = Integer.parseInt(rating);
		
		RatingDermatologist rtd = ratingDermatologistRepository.findByDermatologistAndPatient(dermatologist,patient);
		if(rtd == null) {
			rtd = new RatingDermatologist(patient,dermatologist,ratingg);
		}
		else {
			rtd.setRating(ratingg);
		}
		ratingDermatologistRepository.save(rtd);
		return null;
	}

}
