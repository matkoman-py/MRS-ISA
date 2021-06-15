package pharmacyhub.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pharmacyhub.domain.DermatologistAppointment;
import pharmacyhub.domain.Drug;
import pharmacyhub.domain.DrugPrice;
import pharmacyhub.domain.DrugRequest;
import pharmacyhub.domain.DrugReservation;
import pharmacyhub.domain.DrugStock;
import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.Ingredient;
import pharmacyhub.domain.PharmacistAppointment;
import pharmacyhub.domain.enums.DrugReservationStatus;
import pharmacyhub.domain.users.Patient;
import pharmacyhub.domain.users.Pharmacist;
import pharmacyhub.dto.DrugReservationDto;
import pharmacyhub.dto.DrugReservationEmployeeDto;
import pharmacyhub.dto.search.DrugReservationCancelDto;
import pharmacyhub.repositories.DermatologistAppointmentRepository;
import pharmacyhub.repositories.DrugRepository;
import pharmacyhub.repositories.DrugRequestRepository;
import pharmacyhub.repositories.DrugReservationRepository;
import pharmacyhub.repositories.DrugStockRepository;
import pharmacyhub.repositories.DrugstoreRepository;
import pharmacyhub.repositories.PharmacistAppointmentRepository;
import pharmacyhub.repositories.users.PatientRepository;
import pharmacyhub.repositories.users.PharmacistRepository;
import pharmacyhub.utils.RadnomGeneratorUtil;

@Service
@Transactional
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
	
	@Autowired
	private PatientCategoryService patientCategoryService;
	
	@Autowired
	private DrugStockService drugStockService;
	
	@Autowired
	PharmacistAppointmentRepository pharmAppointmentRepository;
	
	@Autowired
	DermatologistAppointmentRepository dermAppointmentRepository;

	public List<DrugReservation> findAll() {
		return drugreservationRespository.findAll();
	}
	
	//, rollbackFor = OptimisticLockException.class
	@Transactional(readOnly = false)
	private String saveSingleReservation(DrugReservationDto drugreservationDto, boolean eReceit) throws Exception {
		String drugId = drugreservationDto.getDrugId();
		String drugstoreId = drugreservationDto.getDrugstoreId();
		String patientId = drugreservationDto.getPatientId();
		String date = drugreservationDto.getDate();
		Integer amount = drugreservationDto.getAmount();

		Drug drug = drugRepository.findByIdWithLock(drugId);

		Drugstore drugstore = drugstoreRepository.findById(drugstoreId).orElse(null);
		Patient patient = patientRepository.findById(patientId).orElse(null);
		String confirmationCode = RadnomGeneratorUtil.generateDrugReservationCode(patient.getEmail());

		DrugReservation drr = new DrugReservation(drug, drugstore, amount, patient, date,eReceit);
		drr.setConfirmationCode(confirmationCode);
    
		DrugStock drst = drugstockRepository.findByDrugAndDrugstore(drug, drugstore);
		
		if(drst.getAmount() - amount >= 0) {
			drst.setAmount(drst.getAmount() - amount);
			drugstockRepository.save(drst);
		}
		else if (!hasDrugRequest(drugstore, drug)){
			DrugRequest dr = new DrugRequest(drugstore, drug, false);
			drugRequestRepository.save(dr);
			throw new Exception("Drug not on stock!");
    }		
		//provera za alergicnost
		
		int foundAllergen = 0;
		for(Ingredient ing : patient.getAllergens()) {
			if(drug.getIngredients().contains(ing)) {
				return null;
			}
		}
		
		DrugPrice drugPrice = drugStockService.getLastPriceByDrugAndDrugStore(drug, drugstore);
		drr.setPrice(patientCategoryService.getPriceWithDiscount(patient, drugPrice.getPrice()));
		drugreservationRespository.save(drr);
		
		return drr.getDrug().getName() + ": " + drr.getConfirmationCode();
	}
	
	private boolean hasDrugRequest(Drugstore drugstore, Drug drug) {
		return drugRequestRepository.findByDrugAndDrugstoreAndHandledFalse(drug, drugstore).size() > 0;
	}
	
//	private boolean isDrugOnStock(String drugstoreId, String drugId, int amount) {
//		return drugreservationRespository.existsByDrugstoreIdAndDrugIdAndAmountGreaterThanEqual(drugstoreId, drugId, amount);
//		
//	}
	
//	private boolean areDrugReservationsOnStock(List<DrugReservationDto>  drugReservationDtos) {
//		for (DrugReservationDto drugReservationDto : drugReservationDtos) {
//			if(!isDrugOnStock(drugReservationDto.getDrugstoreId(), drugReservationDto.getDrugId(), drugReservationDto.getAmount())) {
//				return false;
//			}
//		}
//		return true;
//	}
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String saveMultipleReservations(List<DrugReservationDto>  drugReservationDtos) throws Exception {
		String confirmationCodes = "";
		if (drugReservationDtos.isEmpty()) {
			throw new Exception("Empty drug reservations");
		}
		
		Patient patient = patientRepository.findById(drugReservationDtos.get(0).getPatientId()).orElse(null);
//		if(!areDrugReservationsOnStock(drugReservationDtos)) {
//			throw new Exception("Not enough drug on stock");
//		}
		
		for (DrugReservationDto drugReservationDto : drugReservationDtos) {
			System.out.println(drugReservationDto.getAmount());
			confirmationCodes += "<br/>" + saveSingleReservation(drugReservationDto, true);
		}
		userNotificationService.sendReservationConfirmationDrug(patient.getEmail(), confirmationCodes);
		
		return "Success!";
	}

	public String saveReservation(DrugReservationDto drugreservationDto) throws Exception {
		String confirmationCode = saveSingleReservation(drugreservationDto, false);
		if(confirmationCode != null) {
			Patient patient = patientRepository.findById(drugreservationDto.getPatientId()).orElse(null);
			userNotificationService.sendReservationConfirmationDrug(patient.getEmail(), confirmationCode);
			return "Success!";
		}else {
			return "Not enough drug on stock!";
		}
		
	}

	public List<DrugReservation> getPatientReservations(String patientId, Pageable pageable) {
		List<DrugReservation> reservations = drugreservationRespository.findByPatient(patientRepository.findById(patientId).orElse(null),pageable);
		return reservations;
	}

	@Transactional(readOnly=false, rollbackFor = PessimisticLockingFailureException.class)
	public List<DrugReservation> cancelReservation(DrugReservationCancelDto drugreservationcancelDto) {
		String drugReservationId = drugreservationcancelDto.getDrugReservationId();
		String patientId = drugreservationcancelDto.getPatientId();
		Drug drug = drugreservationRespository.findById(drugReservationId).orElse(null).getDrug();
		Drugstore drugstore = drugreservationRespository.findById(drugReservationId).orElse(null).getDrugstore();
		
		DrugStock drst = drugstockRepository.findByDrugAndDrugstore(drug, drugstore);
		
		drst.setAmount(drst.getAmount() + drugreservationRespository.findById(drugReservationId).orElse(null).getAmount());
		drugstockRepository.save(drst);
		drugreservationRespository.deleteById(drugReservationId);
		
		List<DrugReservation> reservations =  drugreservationRespository.findByPatientId(patientId);
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
			
			if(dateRes.after(dateNow) && !dr.getStatus().equals(DrugReservationStatus.Issued)) {
				wanted.add(dr);
			}
		}
		return wanted;
	}

	public String issueReservation(String reservationId,String confirmationCode) throws ParseException, MessagingException {
		DrugReservation dr = drugreservationRespository.findById(reservationId).orElse(null);
		if(dr.getConfirmationCode().equals(confirmationCode)) {
			Date dateRes = new SimpleDateFormat("yyyy-MM-dd").parse(dr.getDate());
			Date dateNow = new Date(System.currentTimeMillis()+24*60*60*1000);
			if(dateRes.after(dateNow)) {
				dr.setStatus(DrugReservationStatus.Issued);
				userNotificationService.sendPickUpConfirmation(dr.getPatient().getEmail(),dr.getDrug().getName(), new Date().toString());
				patientCategoryService.updatePatientCategory(dr.getPatient(), dr.getDrug().getPoint());
				return "Confirmation code is valid, drug is issued!";
			}
			return "Pick up date is in the next 24h! Drug not issued!";
		}else {
			return "Confirmation code is not valid!";
		}
	}

	public Integer getPatientReservations(String patientId) {
		return  drugreservationRespository.findByPatient(patientRepository.findById(patientId).orElse(null)).size();
  }
  
	public String saveReservationEmployee(DrugReservationEmployeeDto drugreservationEmployeeDto) throws Exception {
		
		DrugReservationDto drugreservationDto = new DrugReservationDto(
				drugreservationEmployeeDto.getPatientId(),
				drugreservationEmployeeDto.getDrugstoreId(),
				drugreservationEmployeeDto.getDrugId(),
				drugreservationEmployeeDto.getDate(),
				drugreservationEmployeeDto.getAmount()
		);
		
		String confirmationCode = saveSingleReservation(drugreservationDto,false);
		if(confirmationCode == null) {
			return "Not enough drug on stock!";
		}
		
		if(drugreservationEmployeeDto.getCheck().equals("derm")) {
			DermatologistAppointment da = dermAppointmentRepository.findById(drugreservationEmployeeDto.getAppointmentId()).orElse(null);
			if(da.getAppointmentReport() != null) {
				da.setAppointmentReport(da.getAppointmentReport()+"\nReserved: -" + drugRepository.findById(drugreservationEmployeeDto.getDrugId()).orElse(null).getName()
				+"*"+drugreservationEmployeeDto.getAmount()+"\nTherapy duration: "+ drugreservationEmployeeDto.getDuration()+" days.\n");}
				else {
					da.setAppointmentReport("\nReserved: -" + drugRepository.findById(drugreservationEmployeeDto.getDrugId()).orElse(null).getName()
							+"*"+drugreservationEmployeeDto.getAmount()+"\nTherapy duration: "+ drugreservationEmployeeDto.getDuration()+" days.\n");
				}
			dermAppointmentRepository.save(da);
		}else {
			PharmacistAppointment da = pharmAppointmentRepository.findById(drugreservationEmployeeDto.getAppointmentId()).orElse(null);
			if(da.getAppointmentReport() != null) {
			da.setAppointmentReport(da.getAppointmentReport()+"\nReserved: -" + drugRepository.findById(drugreservationEmployeeDto.getDrugId()).orElse(null).getName()
			+"*"+drugreservationEmployeeDto.getAmount()+"\nTherapy duration: "+ drugreservationEmployeeDto.getDuration()+" days.\n");}
			else {
				da.setAppointmentReport("\nReserved: -" + drugRepository.findById(drugreservationEmployeeDto.getDrugId()).orElse(null).getName()
						+"*"+drugreservationEmployeeDto.getAmount()+"\nTherapy duration: "+ drugreservationEmployeeDto.getDuration()+" days.\n");
			}
			pharmAppointmentRepository.save(da);
		}
		
		Patient patient = patientRepository.findById(drugreservationDto.getPatientId()).orElse(null);
		userNotificationService.sendReservationConfirmationDrug(patient.getEmail(), confirmationCode);
		return "Success!";
	}

}
