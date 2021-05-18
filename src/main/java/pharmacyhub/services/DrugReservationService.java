package pharmacyhub.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.DrugRequest;
import pharmacyhub.domain.DrugReservation;
import pharmacyhub.domain.DrugStock;
import pharmacyhub.domain.users.Patient;
import pharmacyhub.domain.users.Pharmacist;
import pharmacyhub.dto.DrugReservationDto;
import pharmacyhub.dto.search.DrugReservationCancelDto;
import pharmacyhub.repositories.DrugRepository;
import pharmacyhub.repositories.DrugRequestRepository;
import pharmacyhub.repositories.DrugReservationRepository;
import pharmacyhub.repositories.DrugStockRepository;
import pharmacyhub.repositories.DrugstoreRepository;
import pharmacyhub.repositories.users.PatientRepository;
import pharmacyhub.repositories.users.PharmacistRepository;
import pharmacyhub.utils.RadnomGeneratorUtil;

@Service
public class DrugReservationService {

	@Autowired
	private DrugReservationRepository drugreservationRespository;

	@Autowired
	private DrugstoreRepository drugstoreRepository;

	@Autowired
	private DrugRepository drugRepository;

	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private PharmacistRepository pharmacistRepository;

	@Autowired
	private DrugStockRepository drugstockRepository;

	@Autowired
	private UserNotificationService userNotificationService;
	
	@Autowired
	private DrugRequestRepository drugRequestRepository;

	public List<DrugReservation> findAll() {
		return drugreservationRespository.findAll();
	}

	public String saveReservation(DrugReservationDto drugreservationDto) throws MessagingException {
		String drugId = drugreservationDto.getDrugId();
		String drugstoreId = drugreservationDto.getDrugstoreId();
		String patientId = drugreservationDto.getPatientId();
		String date = drugreservationDto.getDate();

		Patient patient = patientRepository.findById(patientId).orElse(null);

		String confirmationCode = RadnomGeneratorUtil.generateDrugReservationCode(patient.getEmail());

		DrugReservation drr = new DrugReservation(drugRepository.findById(drugId).orElse(null),
				drugstoreRepository.findById(drugstoreId).orElse(null), 1,
				patientRepository.findById(patientId).orElse(null), date);
		drr.setConfirmationCode(confirmationCode);

		drugreservationRespository.save(drr);
		List<DrugStock> drst = drugstockRepository.findByDrugId(drugId);
		for(DrugStock stok:drst) {
			if(stok.getDrugstore().getId().equals(drugstoreId)) {
				if(stok.getAmount()-1>=0) {
					stok.setAmount(stok.getAmount() - 1);
					drugstockRepository.save(stok);
				}
				else {
					//NAPRAVITI DRUG REQUEST !!!
					DrugRequest dr = new DrugRequest(drugstoreRepository.findById(drugstoreId).orElse(null),drugRepository.findById(drugId).orElse(null),false);
					drugRequestRepository.save(dr);
					return "Drug not on stock!";
				}
			}
		}
		userNotificationService.sendReservationConfirmationDrug(patient.getEmail(), drr.getConfirmationCode());
		return "Success!";
	}

	public List<DrugReservation> getPatientReservations(String patientId) {
		List<DrugReservation> reservations = drugreservationRespository.findByPatient(patientRepository.findById(patientId).orElse(null));
		return reservations;
	}

	public List<DrugReservation> cancelReservation(DrugReservationCancelDto drugreservationcancelDto) {
		String drugReservationId = drugreservationcancelDto.getDrugReservationId();
		String patientId = drugreservationcancelDto.getPatientId();
		String drugId = drugreservationRespository.findById(drugReservationId).orElse(null).getDrug().getId();
		String drugstoreId = drugreservationRespository.findById(drugReservationId).orElse(null).getDrugstore().getId();
		
		drugreservationRespository.deleteById(drugReservationId);
		List<DrugStock> drst = drugstockRepository.findByDrugId(drugId);
		for(DrugStock stok:drst) {
			if(stok.getDrugstore().getId().equals(drugstoreId)) {
				stok.setAmount(stok.getAmount() + 1);
				drugstockRepository.save(stok);
			}
		}
		List<DrugReservation> reservations = drugreservationRespository.findByPatient(patientRepository.findById(patientId).orElse(null));
		System.out.println(reservations.size());
		return reservations;
	}

	public List<DrugReservation> getPatientReservationsInDrugstore(String patientEmail, String pharmacistId) throws ParseException {
		Pharmacist pharm = pharmacistRepository.findById(pharmacistId).orElse(null);
		//System.out.println(pharm.getDrugstore().getName());
		//System.out.println(patientEmail);
		List<DrugReservation> allPatient = drugreservationRespository.findByDrugstoreAndPatient(pharm.getDrugstore(),patientRepository.findByEmail(patientEmail));
		ArrayList<DrugReservation> wanted = new ArrayList<DrugReservation>();
		for(DrugReservation dr : allPatient) {
			Date dateRes=new SimpleDateFormat("yyyy-MM-dd").parse(dr.getDate()); 
			Date dateNow = new Date(System.currentTimeMillis()-24*60*60*1000);
			
			if(dateRes.after(dateNow)) {
				wanted.add(dr);
			}
		}
		return wanted;
	}

	public String issueReservation(String reservationId,String confirmationCode) throws ParseException, MessagingException {
		DrugReservation dr = drugreservationRespository.findById(reservationId).orElse(null);
		if(dr.getConfirmationCode().equals(confirmationCode)) {
			Date dateRes=new SimpleDateFormat("yyyy-MM-dd").parse(dr.getDate());
			Date dateNow = new Date(System.currentTimeMillis()+24*60*60*1000);
			if(dateRes.after(dateNow)) {
				userNotificationService.sendPickUpConfirmation(dr.getPatient().getEmail(),dr.getDrug().getName(), new Date().toString());
				return "Confirmation code is valid, drug is issued!";
			}
			return "Pick up date is in the next 24h! Drug not issued!";
		}else {
			return "Confirmation code is not valid!";
		}
	}

}
