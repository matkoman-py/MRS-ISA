package pharmacyhub.services;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pharmacyhub.domain.AbsenceRequest;
import pharmacyhub.domain.DermatologistAppointment;
import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.PharmacistAppointment;
import pharmacyhub.domain.RatingDrugstore;
import pharmacyhub.domain.RatingPharmacist;
import pharmacyhub.domain.enums.AbsenceRequestStatus;
import pharmacyhub.domain.users.Patient;
import pharmacyhub.domain.users.Pharmacist;
import pharmacyhub.dto.DrugstoreDto;
import pharmacyhub.dto.EmployeeOverviewDto;
import pharmacyhub.dto.PharmacistAppointmentPatientDto;
import pharmacyhub.dto.PharmacistDto;
import pharmacyhub.repositories.AbsenceRequestRepository;
import pharmacyhub.repositories.DermatologistAppointmentRepository;
import pharmacyhub.repositories.DrugstoreRepository;
import pharmacyhub.repositories.PharmacistAppointmentRepository;
import pharmacyhub.repositories.RatingDrugstoreRepository;
import pharmacyhub.repositories.RatingPharmacistRepository;
import pharmacyhub.repositories.users.PatientRepository;
import pharmacyhub.repositories.users.PharmacistRepository;

@Service
@Transactional
public class PharmacistAppointmentService {
	
	@Autowired 
	private DermatologistAppointmentRepository dermatologistAppointmentRepository;
	
	@Autowired 
	private PharmacistAppointmentRepository pharmacistAppointmentRepository;
	
	@Autowired 
	private DrugstoreRepository drugstoreRepository;
	
	@Autowired 
	private PatientRepository patientRepository;
	
	@Autowired	
	private RatingPharmacistRepository ratingPharmacistRepository;
	
	@Autowired
	private PharmacistRepository pharmacistRepository;
	
    @Autowired
	private UserNotificationService userNotificationService;
    
    @Autowired
    private RatingDrugstoreRepository ratingDrugstoreRepository;
    
	@Autowired
	private PatientCategoryService patientCategoryService;

	@Autowired
	private AbsenceRequestRepository absenceRequestRepository;
	
    public List<PharmacistAppointment> getAppointments(String patientId, Pageable pageable) {
		return pharmacistAppointmentRepository.findByPatientId(patientId,pageable);
	}
    
    public int getAppointmentsLength(String patientId) {
		return pharmacistAppointmentRepository.findByPatientId(patientId).size();
	}
    

    @Transactional(rollbackFor = Exception.class)
	public PharmacistAppointment saveWithPatient(PharmacistAppointmentPatientDto pharmacistAppointmentPatientDto) throws Exception {
		
		Date vreme = pharmacistAppointmentPatientDto.getDate();
		vreme.setHours(pharmacistAppointmentPatientDto.getTime().getHours());
		vreme.setMinutes(pharmacistAppointmentPatientDto.getTime().getMinutes());
		long vremePocetak = vreme.getTime();
		vreme.setMinutes(pharmacistAppointmentPatientDto.getTime().getMinutes()+pharmacistAppointmentPatientDto.getDuration());
		long vremeKraj = vreme.getTime();
		

		List<AbsenceRequest> absenceRequests = absenceRequestRepository.findByEmployeeAndStatus(pharmacistRepository.findById(pharmacistAppointmentPatientDto.getPharmacistId()).orElse(null), AbsenceRequestStatus.Approved);
		for(AbsenceRequest ar : absenceRequests) {
			if(ar.getStartDate().before(vreme) && ar.getEndDate().after(vreme)) {
				throw new Exception("Pharmacist is on absence at that time.");
			}
		}
		
		List<PharmacistAppointment> pharmacistAppointments = pharmacistAppointmentRepository.findByPatientIdOrderById(pharmacistAppointmentPatientDto.getPatientId());
		System.out.println(pharmacistAppointments.size());
		for(PharmacistAppointment pa : pharmacistAppointments) {
			Date pVreme = pa.getDate();
			pVreme.setHours(pa.getTime().getHours());
			pVreme.setMinutes(pa.getTime().getMinutes());
			long pvremePocetak = pVreme.getTime();
			pVreme.setHours(pa.getTimeEnd().getHours());
			pVreme.setMinutes(pa.getTimeEnd().getMinutes());
			long pvremeKraj = pVreme.getTime();
			
			if(vremePocetak >= pvremePocetak && vremePocetak<=pvremeKraj) {
				throw new Exception("Patient already has an appointment at that time.");
			}
			if(vremeKraj >= pvremePocetak && vremeKraj<=pvremeKraj) {
				throw new Exception("Patient already has an appointment at that time.");
			}
		}
		
		
		List<PharmacistAppointment> pharmacistAppointmentsPharm = pharmacistAppointmentRepository.findByPharmacistIdOrderById(pharmacistAppointmentPatientDto.getPharmacistId());
		
		for(PharmacistAppointment pa : pharmacistAppointmentsPharm) {
			System.out.println(pa.getDate());
			System.out.println(pa.getTime());
			Date pVreme = pa.getDate();
			pVreme.setHours(pa.getTime().getHours());
			pVreme.setMinutes(pa.getTime().getMinutes());
			long pvremePocetak = pVreme.getTime();
			pVreme.setHours(pa.getTimeEnd().getHours());
			pVreme.setMinutes(pa.getTimeEnd().getMinutes());
			long pvremeKraj = pVreme.getTime();
			
			if(vremePocetak >= pvremePocetak && vremePocetak<=pvremeKraj) {
				throw new Exception("Pharmacist already has an appointment at that time.");
			}
			if(vremeKraj >= pvremePocetak && vremeKraj<=pvremeKraj) {
				throw new Exception("Pharmacist already has an appointment at that time.");
			}
		}
		
		
		List<DermatologistAppointment> dermatologistAppointmentsPatient = dermatologistAppointmentRepository.findByPatientId(pharmacistAppointmentPatientDto.getPatientId());
		
		for(DermatologistAppointment da : dermatologistAppointmentsPatient) {
			Date pVreme = da.getDate();
			pVreme.setHours(da.getTime().getHours());
			pVreme.setMinutes(da.getTime().getMinutes());
			long pvremePocetak = pVreme.getTime();
			pVreme.setHours(da.getTimeEnd().getHours());
			pVreme.setMinutes(da.getTimeEnd().getMinutes());
			long pvremeKraj = pVreme.getTime();
			if(vremePocetak >= pvremePocetak && vremePocetak<=pvremeKraj) {
				throw new Exception("Patient already has an appointment at that time.");
			}
			if(vremeKraj >= pvremePocetak && vremeKraj<=pvremeKraj) {
				throw new Exception("Patient already has an appointment at that time.");
			}
		}
		
		Pharmacist ph = pharmacistRepository.findById(pharmacistAppointmentPatientDto.getPharmacistId()).orElse(null);
		String hours1 = ph.getWorkingHoursFrom().substring(0,2);
		int workingFrom = Integer.parseInt(hours1) * 3600;
		String minutes1 = ph.getWorkingHoursFrom().substring(3,5);
		workingFrom += Integer.parseInt(minutes1) * 60;
		
		String hours2 = ph.getWorkingHoursTo().substring(0,2);
		int workingTo = Integer.parseInt(hours2) * 3600;
		String minutes2 = ph.getWorkingHoursTo().substring(3,5);
		workingTo += Integer.parseInt(minutes2) * 60;
		
		int hours = pharmacistAppointmentPatientDto.getTime().getHours();
		int inputTime = hours * 3600;
		int minutes = pharmacistAppointmentPatientDto.getTime().getMinutes();
		inputTime += minutes * 60;
		int inputTimeTo = inputTime + pharmacistAppointmentPatientDto.getDuration()*60;
		if(inputTime<=workingFrom || inputTime>=workingTo) {
			throw new Exception("Pharmacist is not working at that time.");
		}
		if(inputTimeTo >= workingTo) {
			throw new Exception("Pharmacist is finishing his shift at that time.");
		}
		Pharmacist pharmacist = pharmacistRepository.findById(pharmacistAppointmentPatientDto.getPharmacistId()).orElse(null);
		Patient patient = patientRepository.findById(pharmacistAppointmentPatientDto.getPatientId()).orElse(null);
		
    	userNotificationService.sendReservationConfirmation(patientRepository.getById(pharmacistAppointmentPatientDto.getPatientId()).getEmail(), "pharmacist");
    	PharmacistAppointment pharmacistAppointment = new PharmacistAppointment(
    			pharmacist,
    			pharmacistAppointmentPatientDto.getDate(), 
    			pharmacistAppointmentPatientDto.getTime(),
    			pharmacistAppointmentPatientDto.getDuration(),
    			patient,
    			null,
    			false,
    			patientCategoryService.getPriceWithDiscount(patient, pharmacist.getDrugstore().getPharmacistAppointmentPrice()));
		return pharmacistAppointmentRepository.save(pharmacistAppointment);
	}
	
	public PharmacistAppointment findAppointment(String pharmacistAppointmentId) {
		PharmacistAppointment pa = pharmacistAppointmentRepository.findById(pharmacistAppointmentId).orElse(null);
		return pa;
	}

	public List<Drugstore> findDrugstores(String pharmacistAppointmentTime, String pharmacistAppointmentDate) {
		List<Pharmacist> allPharmacists = pharmacistRepository.findAll();
		List<Drugstore>  wantedDrugstores = new ArrayList<>();
		List<DrugstoreDto> wantedDrugstores1 = new ArrayList<>();
		
		String hours = pharmacistAppointmentTime.substring(0,2);
		int inputTime = Integer.parseInt(hours) * 3600;
		String minutes = pharmacistAppointmentTime.substring(3,5);
		inputTime += Integer.parseInt(minutes) * 60;
		
		for(Pharmacist ph : allPharmacists) {
			String hours1 = ph.getWorkingHoursFrom().substring(0,2);
			int workingFrom = Integer.parseInt(hours1) * 3600;
			String minutes1 = ph.getWorkingHoursFrom().substring(3,5);
			workingFrom += Integer.parseInt(minutes1) * 60;
			
			
			String hours2 = ph.getWorkingHoursTo().substring(0,2);
			int workingTo = Integer.parseInt(hours2) * 3600;
			String minutes2 = ph.getWorkingHoursTo().substring(3,5);
			workingTo += Integer.parseInt(minutes2) * 60;
			
			if(inputTime > workingFrom && inputTime < workingTo) {
				if(!wantedDrugstores.contains(drugstoreRepository.findById(ph.getDrugstore().getId()).orElse(null))){
					if(findPharmacists(ph.getDrugstore().getId(),pharmacistAppointmentDate,pharmacistAppointmentTime).size() > 0) {
						wantedDrugstores.add(drugstoreRepository.findById(ph.getDrugstore().getId()).orElse(null));
					}
				}
			}
		}
		return wantedDrugstores;
	}

	public List<PharmacistDto> findPharmacists(String drugstoreId,String pharmacistAppointmentDate,String pharmacistAppointmentTime) {
		List<Pharmacist> allPharmacists =  pharmacistRepository.findByDrugstore(drugstoreRepository.findById(drugstoreId).orElse(null));
		List<Pharmacist> wantedPharmacist = new ArrayList<>();
		List<PharmacistDto> wantedPharmacist1 = new ArrayList<>();
		
		pharmacistAppointmentRepository.findAll();
		System.out.println(pharmacistAppointmentTime);
		Time in = new Time(Integer.parseInt(pharmacistAppointmentTime.substring(0,2)),Integer.parseInt(pharmacistAppointmentTime.substring(3,5)),0);
		long inputTime = (Integer.parseInt(pharmacistAppointmentTime.substring(0,2)) * 3600 + Integer.parseInt(pharmacistAppointmentTime.substring(3,5)) * 60) * 1000;
		
		for(Pharmacist ph:allPharmacists) {
			boolean free = true;
			List<PharmacistAppointment> Appointments = pharmacistAppointmentRepository.findByPharmacistId(ph.getId());
			for(PharmacistAppointment Appointment:Appointments) {
				if(Appointment.getDate().toString().contains(pharmacistAppointmentDate)) {
					
					long busyFrom = Appointment.getTime().getTime();
					long busyTo = busyFrom + Appointment.getDuration()*60000;
					
					if(inputTime >= busyFrom && inputTime <= busyTo) {
						free = false;
						break;
					}
				}
			}
			
			String hours1 = ph.getWorkingHoursFrom().substring(0,2);
			int workingFrom = Integer.parseInt(hours1) * 3600 * 1000;
			String minutes1 = ph.getWorkingHoursFrom().substring(3,5);
			workingFrom += Integer.parseInt(minutes1) * 60 * 1000;
			
			
			String hours2 = ph.getWorkingHoursTo().substring(0,2);
			int workingTo = Integer.parseInt(hours2) * 3600 * 1000;
			String minutes2 = ph.getWorkingHoursTo().substring(3,5);
			workingTo += Integer.parseInt(minutes2) * 60 * 1000;
			
			System.out.println("Input time " + inputTime + "  wokringfrom: " + workingFrom + "  workingTo: " + workingTo);
			
			if(inputTime < workingFrom || inputTime > workingTo) {
				free = false;
			}
			
			if(free == true) wantedPharmacist.add(ph);
		}
		for(Pharmacist e:wantedPharmacist) {
			wantedPharmacist1.add(new PharmacistDto(e.getId(),e.getName(),e.getSurname(),e.getEmail(),e.getRating()));
		}
		return wantedPharmacist1; 
  }
   
	public List<PharmacistAppointment> getAllPharmacistAppointments(String pharmacistId) {
		List<PharmacistAppointment> allAppointments = pharmacistAppointmentRepository.findAll();
		List<PharmacistAppointment> wantedAppontments = new ArrayList<>();
    
		for(PharmacistAppointment appointment : allAppointments) {
			if(appointment.getPharmacist().getId().equals(pharmacistId))
				wantedAppontments.add(appointment);
		}
		return wantedAppontments;
	}
	
	public PharmacistAppointment endAppointment(String appointmentId, String appointmentReport) {
		PharmacistAppointment da = pharmacistAppointmentRepository.findById(appointmentId).orElse(null);
		if(appointmentReport.equals("0")) {
			da.setAppointmentReport(da.getAppointmentReport());
		}else {
			if(da.getAppointmentReport()!=null) {
				da.setAppointmentReport(appointmentReport+da.getAppointmentReport());
			}else {
				da.setAppointmentReport(appointmentReport);
			}
		
		}
		da.setProcessed(true);
		pharmacistAppointmentRepository.save(da);
		patientCategoryService.updatePatientCategoryFromAppointment(da.getPatient(), "pharmacist");
		return da;
	}
  	
	public List<PharmacistAppointment> findAllPharmacistAppointmentsDone(String pharmacistId, Pageable pageable) {
		return pharmacistAppointmentRepository.findByPharmacistIdAndProcessedTrue(pharmacistId, pageable);
	}
	
	public int findAllPharmacistAppointmentsDoneLength(String pharmacist) {
		return pharmacistAppointmentRepository.findByPharmacistIdAndProcessedTrue(pharmacist).size();
	}
	
	public List<PharmacistAppointment> findAllPharmacistAppointmentsTodo(String pharmacistId, Pageable pageable) {
		return pharmacistAppointmentRepository.findByPharmacistIdAndProcessedFalseAndPatientNotNull(pharmacistId,pageable);
	}

	public int findAllPharmacistAppointmentsTodoLength(String pharmacistId) {
		return pharmacistAppointmentRepository.findByPharmacistIdAndProcessedFalseAndPatientNotNull(pharmacistId).size();
	}

	public Integer reservationsLength(String patientId) {
		return pharmacistAppointmentRepository.findByPatientId(patientId).size();
	}
	
	public List<PharmacistAppointment> cancelAppointment(String appointmentId) {
		PharmacistAppointment da = pharmacistAppointmentRepository.findById(appointmentId).orElse(null);
		String patientId = da.getPatient().getId();
		pharmacistAppointmentRepository.delete(da);
		return pharmacistAppointmentRepository.findByPatientId(patientId);
	}
}
