package pharmacyhub.services;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.DrugReservation;
import pharmacyhub.domain.DrugStock;
import pharmacyhub.dto.DrugReservationDto;
import pharmacyhub.repositories.DrugRepository;
import pharmacyhub.repositories.DrugReservationRepository;
import pharmacyhub.repositories.DrugStockRepository;
import pharmacyhub.repositories.DrugstoreRepository;
import pharmacyhub.repositories.users.PatientRepository;

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
	private DrugStockRepository drugstockRepository;
	
	@Autowired
	private UserNotificationService userNotificationService;
	
	public List<DrugReservation> findAll() {
		return drugreservationRespository.findAll();
	}

	public String saveReservation(DrugReservationDto drugreservationDto) throws MessagingException {
		String drugId = drugreservationDto.getDrugId();
		String drugstoreId = drugreservationDto.getDrugstoreId();
		String patientId = drugreservationDto.getPatientId();
		String date = drugreservationDto.getDate();
		DrugReservation drr = new DrugReservation(drugRepository.findById(drugId).orElse(null),drugstoreRepository.findById(drugstoreId).orElse(null),1,patientRepository.findById(patientId).orElse(null),date);
		drugreservationRespository.save(drr);
		List<DrugStock> drst = drugstockRepository.findByDrugId(drugId);
		for(DrugStock stok:drst) {
			if(stok.getDrugstore().getId().equals(drugstoreId)) {
				stok.setAmount(stok.getAmount() - 1);
				return "Succes!";
			}
		}
		userNotificationService.sendReservationConfirmationDrug(patientRepository.getById(patientId).getEmail());
		return "Succes!";
	}
}
