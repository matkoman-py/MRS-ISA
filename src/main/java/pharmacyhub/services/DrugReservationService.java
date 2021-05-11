package pharmacyhub.services;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.Drug;
import pharmacyhub.domain.DrugReservation;
import pharmacyhub.domain.DrugStock;
import pharmacyhub.domain.users.Patient;
import pharmacyhub.dto.DrugReservationDto;
import pharmacyhub.dto.search.DrugReservationCancelDto;
import pharmacyhub.repositories.DrugRepository;
import pharmacyhub.repositories.DrugReservationRepository;
import pharmacyhub.repositories.DrugStockRepository;
import pharmacyhub.repositories.DrugstoreRepository;
import pharmacyhub.repositories.users.PatientRepository;
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

		Patient patient = patientRepository.findById(patientId).orElse(null);

		String confirmationCode = RadnomGeneratorUtil.generateDrugReservationCode(patient.getEmail());

		DrugReservation drr = new DrugReservation(drugRepository.findById(drugId).orElse(null),
				drugstoreRepository.findById(drugstoreId).orElse(null), 1,
				patientRepository.findById(patientId).orElse(null), date);
		drr.setConfirmationCode(confirmationCode);

		drugreservationRespository.save(drr);
		List<DrugStock> drst = drugstockRepository.findByDrugId(drugId);
		for (DrugStock stok : drst) {
			if (stok.getDrugstore().getId().equals(drugstoreId)) {
				stok.setAmount(stok.getAmount() - 1);
				drugstockRepository.save(stok);
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

}
