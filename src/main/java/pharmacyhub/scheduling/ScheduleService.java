package pharmacyhub.scheduling;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import pharmacyhub.domain.Drug;
import pharmacyhub.domain.DrugReservation;
import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.Penalty;
import pharmacyhub.domain.RatingDermatologist;
import pharmacyhub.domain.RatingDrug;
import pharmacyhub.domain.RatingDrugstore;
import pharmacyhub.domain.RatingPharmacist;
import pharmacyhub.domain.enums.DrugReservationStatus;
import pharmacyhub.domain.users.Dermatologist;
import pharmacyhub.domain.users.Patient;
import pharmacyhub.domain.users.Pharmacist;
import pharmacyhub.repositories.DrugRepository;
import pharmacyhub.repositories.DrugReservationRepository;
import pharmacyhub.repositories.DrugstoreRepository;
import pharmacyhub.repositories.PenaltyRepository;
import pharmacyhub.repositories.RatingDermatologistRepository;
import pharmacyhub.repositories.RatingDrugRepository;
import pharmacyhub.repositories.RatingDrugstoreRepository;
import pharmacyhub.repositories.RatingPharmacistRepository;
import pharmacyhub.repositories.users.DermatologistRepository;
import pharmacyhub.repositories.users.PatientRepository;
import pharmacyhub.repositories.users.PharmacistRepository;

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true)
public class ScheduleService {
	@Autowired
	DrugReservationRepository drugReservationRepository;
	
	@Autowired
	RatingDrugstoreRepository ratingDrugstoreRepository;
	
	@Autowired
	RatingDrugRepository ratingDrugRepository;
	
	@Autowired
	RatingDermatologistRepository ratingDermatologistRepository;
	
	@Autowired
	RatingPharmacistRepository ratingPharmacistRepository;
	
	@Autowired
	DrugstoreRepository drugstoreRepository;
	
	@Autowired
	DrugRepository drugRepository;
	
	@Autowired
	PharmacistRepository pharmacistRepository;
	
	@Autowired
	DermatologistRepository dermatologistRepository;
	
	@Autowired
	PenaltyRepository penaltyRepository;
	
	@Autowired
	PatientRepository patientRepository;
	
	@Scheduled(fixedRateString = "PT24H")
	void drugstoreRating() {
		List<Drugstore> drugstores = drugstoreRepository.findAll();
		for(Drugstore drugstore : drugstores) {
			List<RatingDrugstore> ratings = ratingDrugstoreRepository.findByDrugstore(drugstore);
			double sum=0;
			for(RatingDrugstore rating : ratings) {
				sum+=rating.getRating();
			}
			if(ratings.size() == 0) {
				drugstore.setRating(0);
			}else {
				double rating = sum/ratings.size();
				drugstore.setRating(rating);
			}
			
			drugstoreRepository.save(drugstore);
		}
	}
	
	@Scheduled(fixedRateString = "PT24H")
	void dermatologistRating() {
		List<Dermatologist> dermatologists = dermatologistRepository.findAll();
		for(Dermatologist dermatologist : dermatologists) {
			List<RatingDermatologist> ratings = ratingDermatologistRepository.findByDermatologist(dermatologist);
			double sum=0;
			for(RatingDermatologist rating : ratings) {
				sum+=rating.getRating();
			}
			if(ratings.size() == 0) {
				dermatologist.setRating(0);
			}else {
				double rating = sum/ratings.size();
				dermatologist.setRating(rating);
			}
			dermatologistRepository.save(dermatologist);
		}
	}
	
	@Scheduled(fixedRateString = "PT24H")
	void pharmacistRating() {
		List<Pharmacist> pharmacists = pharmacistRepository.findAll();
		for(Pharmacist pharmacist : pharmacists) {
			List<RatingPharmacist> ratings = ratingPharmacistRepository.findByPharmacist(pharmacist);
			double sum=0;
			for(RatingPharmacist rating : ratings) {
				sum+=rating.getRating();
			}
			if(ratings.size() == 0) {
				pharmacist.setRating(0);
			}else {
				double rating = sum/ratings.size();
				pharmacist.setRating(rating);
			}
			pharmacistRepository.save(pharmacist);
		}
	}
	
	@Scheduled(fixedRateString = "PT24H")
	void drugRating() {
		List<Drug> drugs = drugRepository.findAll();
		for(Drug drug : drugs) {
			List<RatingDrug> ratings = ratingDrugRepository.findByDrug(drug);
			double sum=0;
			for(RatingDrug rating : ratings) {
				sum+=rating.getRating();
			}
			if(ratings.size() == 0) {
				drug.setRating(0);
			}else {
				double rating = sum/ratings.size();
				drug.setRating(rating);
			}
			drugRepository.save(drug);
		}
	}
	@Scheduled(fixedRateString = "PT24H")
	void issuedDrugs() throws ParseException {
		List<DrugReservation> drugReservations = drugReservationRepository.findByStatus(DrugReservationStatus.Pending);
		
		for(DrugReservation dr : drugReservations) {
			System.out.println(dr.getId());
			Date dateRes = new SimpleDateFormat("yyyy-MM-dd").parse(dr.getDate());
			Date dateNow = new Date(System.currentTimeMillis());
			if(dateRes.before(dateNow)) {
				
				Penalty pen = new Penalty( dr.getId(), dr.getPatient().getId() ,  "Drug");
				penaltyRepository.save(pen);
				dr.setStatus(DrugReservationStatus.NoPickup);
				drugReservationRepository.save(dr);
			}
			
		}
	}
	
	@Scheduled(fixedRateString = "PT744H")
	void deletePenalties() {
		//?penaltyRepository.deleteAll();
		List<Patient> patients = patientRepository.findAll();
		for(Patient pat : patients) {
			pat.setPenaltyCounter(0);
			patientRepository.save(pat);
		}
	}
}
