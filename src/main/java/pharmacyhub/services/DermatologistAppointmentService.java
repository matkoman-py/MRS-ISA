package pharmacyhub.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pharmacyhub.domain.AbsenceRequest;
import pharmacyhub.domain.DermatologistAppointment;
import pharmacyhub.domain.Employment;
import pharmacyhub.domain.PharmacistAppointment;
import pharmacyhub.domain.users.Dermatologist;
import pharmacyhub.domain.enums.AbsenceRequestStatus;
import pharmacyhub.domain.users.Patient;
import pharmacyhub.dto.DermatologistAppointmentDto;
import pharmacyhub.dto.DermatologistAppointmentPatientDto;
import pharmacyhub.repositories.AbsenceRequestRepository;
import pharmacyhub.repositories.DermatologistAppointmentRepository;
import pharmacyhub.repositories.DrugstoreRepository;
import pharmacyhub.repositories.EmploymentRepository;
import pharmacyhub.repositories.PharmacistAppointmentRepository;
import pharmacyhub.repositories.users.DermatologistRepository;
import pharmacyhub.repositories.users.PatientRepository;

@Service
@Transactional
public class DermatologistAppointmentService {
	
	@Autowired 
	private DermatologistAppointmentRepository dermatologistAppointmentRepository;
	
	@Autowired 
	private DrugstoreRepository drugstoreRepository;
	
	@Autowired 
	private PatientRepository patientRepository;
	
	@Autowired
	private DermatologistRepository dermatologistRepository;
  
    @Autowired
	private UserNotificationService userNotificationService;
    
    @Autowired
    private PharmacistAppointmentRepository pharmacistAppointmentRepository;
    
    @Autowired
    private EmploymentRepository employmentRepository;
    
	@Autowired
	private PatientCategoryService patientCategoryService;

	@Autowired
	private AbsenceRequestRepository absenceRequestRepository;
	
	public List<DermatologistAppointment> findAll(){
		return dermatologistAppointmentRepository.findAll();
	}
	
	@Transactional(rollbackFor = Exception.class)
	public DermatologistAppointment save(DermatologistAppointmentDto dermatologistAppointmentDto) throws Exception {
		//treba provera da li je dermatolog u datom periodu slobodan
		
		Date vreme = dermatologistAppointmentDto.getDate();
		vreme.setHours(dermatologistAppointmentDto.getTime().getHours());
		vreme.setMinutes(dermatologistAppointmentDto.getTime().getMinutes());
		long vremePocetak = vreme.getTime();
		vreme.setMinutes(dermatologistAppointmentDto.getTime().getMinutes()+dermatologistAppointmentDto.getDuration());
		long vremeKraj = vreme.getTime();
		System.out.println("vreme poc: "+ vremePocetak+ "vreme kraj:" + vremeKraj);
		

		List<AbsenceRequest> absenceRequests = absenceRequestRepository.findByEmployeeAndStatus(dermatologistAppointmentDto.getDermatologist(), AbsenceRequestStatus.Approved);
		for(AbsenceRequest ar : absenceRequests) {
			if(ar.getStartDate().before(vreme) && ar.getEndDate().after(vreme)) {
				throw new Exception("Dermatologist is on absence at that time.");
			}
		}
		
		List<DermatologistAppointment> dermatologistAppointments = dermatologistAppointmentRepository.findByDermatologistIdOrderById(dermatologistAppointmentDto.getDermatologist().getId());
		
		for(DermatologistAppointment da : dermatologistAppointments) {
			Date pVreme = da.getDate();
			pVreme.setHours(da.getTime().getHours());
			pVreme.setMinutes(da.getTime().getMinutes());
			long pvremePocetak = pVreme.getTime();
			pVreme.setHours(da.getTimeEnd().getHours());
			pVreme.setMinutes(da.getTimeEnd().getMinutes());
			long pvremeKraj = pVreme.getTime();
			System.out.println("2 pvreme poc: "+ pvremePocetak+ "pvreme kraj:" + pvremeKraj);
			if(vremePocetak >= pvremePocetak && vremePocetak<=pvremeKraj) {
				System.out.println("USO JE 1");
				throw new Exception("Dermatologist already has an appointment at that time.");
			}
			if(vremeKraj >= pvremePocetak && vremeKraj<=pvremeKraj) {
				System.out.println("USO JE 2");
				throw new Exception("Dermatologist already has an appointment at that time.");
			}
		}
		
		Employment ph = employmentRepository.findByDermatologistIdAndDrugstoreId(dermatologistAppointmentDto.getDermatologist().getId(),dermatologistAppointmentDto.getDrugstoreId());
		String hours1 = ph.getWorkingHoursFrom().substring(0,2);
		int workingFrom = Integer.parseInt(hours1) * 3600;
		String minutes1 = ph.getWorkingHoursFrom().substring(3,5);
		workingFrom += Integer.parseInt(minutes1) * 60;
		
		String hours2 = ph.getWorkingHoursTo().substring(0,2);
		int workingTo = Integer.parseInt(hours2) * 3600;
		String minutes2 = ph.getWorkingHoursTo().substring(3,5);
		workingTo += Integer.parseInt(minutes2) * 60;
		
		int hours = dermatologistAppointmentDto.getTime().getHours();
		int inputTime = hours * 3600;
		int minutes = dermatologistAppointmentDto.getTime().getMinutes();
		inputTime += minutes * 60;

		int inputTimeTo = inputTime + dermatologistAppointmentDto.getDuration()*60;
		
		if(inputTime<=workingFrom || inputTime>=workingTo) {
			System.out.println("inputTime: " + inputTime);
			System.out.println("workingFrom: " + workingFrom);
			System.out.println("workingTo: " + workingTo);
			throw new Exception("Dermatologist is not working at that time.");
		}
		
		if(inputTimeTo >= workingTo) {
			throw new Exception("Dermatologist is finishing his shift at that time.");
		}
		
		return dermatologistAppointmentRepository.save(
				new DermatologistAppointment(
						dermatologistAppointmentDto.getDermatologist(),
						drugstoreRepository.findById(dermatologistAppointmentDto.getDrugstoreId()).orElse(null), 
						dermatologistAppointmentDto.getDate(), 
						dermatologistAppointmentDto.getTime(), 
						dermatologistAppointmentDto.getDuration(), 
						null, 
						null, 
						dermatologistAppointmentDto.getPrice(),
						false));
	}
	
	@Transactional(rollbackFor = Exception.class)
	public DermatologistAppointment saveWithPatient(DermatologistAppointmentPatientDto dermatologistAppointmentPatientDto) throws Exception {
		//treba provera da li je dermatolog u datom periodu slobodan, kao i pacijent
		System.out.println(dermatologistAppointmentPatientDto.getDate());
		System.out.println(dermatologistAppointmentPatientDto.getTime());
		
		Date vreme = dermatologistAppointmentPatientDto.getDate();
		vreme.setHours(dermatologistAppointmentPatientDto.getTime().getHours());
		vreme.setMinutes(dermatologistAppointmentPatientDto.getTime().getMinutes());
		long vremePocetak = vreme.getTime();
		vreme.setMinutes(dermatologistAppointmentPatientDto.getTime().getMinutes()+dermatologistAppointmentPatientDto.getDuration());
		long vremeKraj = vreme.getTime();
		System.out.println("vreme poc: "+ vremePocetak+ "vreme kraj:" + vremeKraj);
		
		
		List<AbsenceRequest> absenceRequests = absenceRequestRepository.findByEmployeeAndStatus(dermatologistRepository.findById(dermatologistAppointmentPatientDto.getDermatologistId()).orElse(null), AbsenceRequestStatus.Approved);
		for(AbsenceRequest ar : absenceRequests) {
			if(ar.getStartDate().before(vreme) && ar.getEndDate().after(vreme)) {
				throw new Exception("Dermatologist is on absence at that time.");
			}
		}
		
		List<PharmacistAppointment> pharmacistAppointments = pharmacistAppointmentRepository.findByPatientId(dermatologistAppointmentPatientDto.getPatientId());
		for(PharmacistAppointment pa : pharmacistAppointments) {
			System.out.println(pa.getDate());
			System.out.println(pa.getTime());
			Date pVreme = pa.getDate();
			pVreme.setHours(pa.getTime().getHours());
			pVreme.setMinutes(pa.getTime().getMinutes());
			long pvremePocetak = pVreme.getTime();
			pVreme.setHours(pa.getTimeEnd().getHours());
			pVreme.setMinutes(pa.getTimeEnd().getMinutes());
			long pvremeKraj = pVreme.getTime();
			System.out.println("pvreme poc: "+ pvremePocetak+ "pvreme kraj:" + pvremeKraj);
			
			if(vremePocetak >= pvremePocetak && vremePocetak<=pvremeKraj) {
				System.out.println("USO JE 1");
				throw new Exception("Patient already has an appointment at that time.");
				// kako throw exception
			}
			if(vremeKraj >= pvremePocetak && vremeKraj<=pvremeKraj) {
				System.out.println("USO JE 2");
				throw new Exception("Patient already has an appointment at that time.");
				// kako throw exception
			}
		}
		
		List<DermatologistAppointment> dermatologistAppointments = dermatologistAppointmentRepository.findByDermatologistId(dermatologistAppointmentPatientDto.getDermatologistId());
		for(DermatologistAppointment da : dermatologistAppointments) {
			Date pVreme = da.getDate();
			pVreme.setHours(da.getTime().getHours());
			pVreme.setMinutes(da.getTime().getMinutes());
			long pvremePocetak = pVreme.getTime();
			pVreme.setHours(da.getTimeEnd().getHours());
			pVreme.setMinutes(da.getTimeEnd().getMinutes());
			long pvremeKraj = pVreme.getTime();
			System.out.println("2 pvreme poc: "+ pvremePocetak+ "pvreme kraj:" + pvremeKraj);
			if(vremePocetak >= pvremePocetak && vremePocetak<=pvremeKraj) {
				System.out.println("USO JE 3");
				throw new Exception("Dermatologist already has an appointment at that time.");
				// kako throw exception
			}
			if(vremeKraj >= pvremePocetak && vremeKraj<=pvremeKraj) {
				System.out.println("USO JE 4");
				throw new Exception("Dermatologist already has an appointment at that time.");
				// kako throw exception
			}
		}
		
		
		
		List<DermatologistAppointment> dermatologistAppointmentsPatient = dermatologistAppointmentRepository.findByPatientId(dermatologistAppointmentPatientDto.getPatientId());
		for(DermatologistAppointment da : dermatologistAppointmentsPatient) {
			Date pVreme = da.getDate();
			pVreme.setHours(da.getTime().getHours());
			pVreme.setMinutes(da.getTime().getMinutes());
			long pvremePocetak = pVreme.getTime();
			pVreme.setHours(da.getTimeEnd().getHours());
			pVreme.setMinutes(da.getTimeEnd().getMinutes());
			long pvremeKraj = pVreme.getTime();
			System.out.println("3 pvreme poc: "+ pvremePocetak+ "pvreme kraj:" + pvremeKraj);
			if(vremePocetak >= pvremePocetak && vremePocetak<=pvremeKraj) {
				System.out.println("USO JE 5");
				throw new Exception("Patient already has an appointment at that time.");
				// kako throw exception
			}
			if(vremeKraj >= pvremePocetak && vremeKraj<=pvremeKraj) {
				System.out.println("USO JE 6");
				throw new Exception("Patient already has an appointment at that time.");
				// kako throw exception
			}
		}
		
		Employment ph = employmentRepository.findByDermatologistIdAndDrugstoreId(dermatologistAppointmentPatientDto.getDermatologistId(),dermatologistAppointmentPatientDto.getDrugstoreId());
		String hours1 = ph.getWorkingHoursFrom().substring(0,2);
		int workingFrom = Integer.parseInt(hours1) * 3600;
		String minutes1 = ph.getWorkingHoursFrom().substring(3,5);
		workingFrom += Integer.parseInt(minutes1) * 60;
		
		String hours2 = ph.getWorkingHoursTo().substring(0,2);
		int workingTo = Integer.parseInt(hours2) * 3600;
		String minutes2 = ph.getWorkingHoursTo().substring(3,5);
		workingTo += Integer.parseInt(minutes2) * 60;
		
		int hours = dermatologistAppointmentPatientDto.getTime().getHours();
		int inputTime = hours * 3600;
		int minutes = dermatologistAppointmentPatientDto.getTime().getMinutes();
		inputTime += minutes * 60;
		int inputTimeTo = inputTime + dermatologistAppointmentPatientDto.getDuration()*60;
		if(inputTime<=workingFrom || inputTime>=workingTo) {
			throw new Exception("Dermatologist is not working at that time.");
		}
		if(inputTimeTo >= workingTo) {
			throw new Exception("Dermatologist is finishing his shift at that time.");
		}
		Patient patient = patientRepository.findById(dermatologistAppointmentPatientDto.getPatientId()).orElse(null);
		
		userNotificationService.sendReservationConfirmation(patientRepository.findById(dermatologistAppointmentPatientDto.getPatientId()).orElse(null).getEmail(), "dermatologist");
		return dermatologistAppointmentRepository.save(
				new DermatologistAppointment(
						dermatologistRepository.findById(dermatologistAppointmentPatientDto.getDermatologistId()).orElse(null), 
						drugstoreRepository.findById(dermatologistAppointmentPatientDto.getDrugstoreId()).orElse(null), 
						dermatologistAppointmentPatientDto.getDate(), 
						dermatologistAppointmentPatientDto.getTime(), 
						dermatologistAppointmentPatientDto.getDuration(), 
						patient, 
						null, 
						patientCategoryService.getPriceWithDiscount(patient, dermatologistAppointmentPatientDto.getPrice()),
						false));
	}
	
	//List<DermatologistAppointment>
	public List<DermatologistAppointment> findAvailable(String drugstoreId) {
		List<DermatologistAppointment> allAppointments = findAll();
		List<DermatologistAppointment> wantedAppontments = new ArrayList<>();
		String str = ""; 
		for(DermatologistAppointment appointment : allAppointments) {
			str += appointment.getDrugstore().getId() + "    " +  drugstoreId + "\n";
			System.out.println(str);
			if(appointment.getDrugstore().getId().equals(drugstoreId) && (appointment.getPatient() == null))
				wantedAppontments.add(appointment);
		}
		return wantedAppontments;
	}

	@Transactional(rollbackFor = Exception.class)
	public List<DermatologistAppointment> createReservation(String patientId, String appointmentId, String drugstoreId) throws Exception {
		List<DermatologistAppointment> wantedAppontments = new ArrayList<>();
		
		DermatologistAppointment selectedAppointment = dermatologistAppointmentRepository.findOneById(appointmentId);
		Patient patient = patientRepository.findById(patientId).orElse(null);
		
		if (patient == null) {
			throw new RuntimeException("This patient doesn't exist!");
		}
		
		if (selectedAppointment == null) {
			throw new RuntimeException("This appointment doesn't exist!");
		}
		
		if (selectedAppointment.getPatient() != null) {
			throw new RuntimeException("This appointemt already has a patient!");
		}
		
		Date vreme = selectedAppointment.getDate();
		vreme.setHours(selectedAppointment.getTime().getHours());
		vreme.setMinutes(selectedAppointment.getTime().getMinutes());
		long vremePocetak = vreme.getTime();
		vreme.setMinutes(selectedAppointment.getTime().getMinutes()+selectedAppointment.getDuration());
		long vremeKraj = vreme.getTime();
		
		List<DermatologistAppointment> dermatologistAppointmentsPatient = dermatologistAppointmentRepository.findByPatientId(patientId);
		for(DermatologistAppointment da : dermatologistAppointmentsPatient) {
			Date pVreme = da.getDate();
			pVreme.setHours(da.getTime().getHours());
			pVreme.setMinutes(da.getTime().getMinutes());
			long pvremePocetak = pVreme.getTime();
			pVreme.setHours(da.getTimeEnd().getHours());
			pVreme.setMinutes(da.getTimeEnd().getMinutes());
			long pvremeKraj = pVreme.getTime();
			System.out.println("3 pvreme poc: "+ pvremePocetak+ "pvreme kraj:" + pvremeKraj);
			if(vremePocetak >= pvremePocetak && vremePocetak<=pvremeKraj) {
				System.out.println("USO JE 5");
				throw new Exception("Patient already has an appointment at that time.");
				// kako throw exception
			}
			if(vremeKraj >= pvremePocetak && vremeKraj<=pvremeKraj) {
				System.out.println("USO JE 6");
				throw new Exception("Patient already has an appointment at that time.");
				// kako throw exception
			}
		}
		
		List<PharmacistAppointment> pharmacistAppointments = pharmacistAppointmentRepository.findByPatientId(patientId);
		for(PharmacistAppointment pa : pharmacistAppointments) {
			System.out.println(pa.getDate());
			System.out.println(pa.getTime());
			Date pVreme = pa.getDate();
			pVreme.setHours(pa.getTime().getHours());
			pVreme.setMinutes(pa.getTime().getMinutes());
			long pvremePocetak = pVreme.getTime();
			pVreme.setHours(pa.getTimeEnd().getHours());
			pVreme.setMinutes(pa.getTimeEnd().getMinutes());
			long pvremeKraj = pVreme.getTime();
			System.out.println("pvreme poc: "+ pvremePocetak+ "pvreme kraj:" + pvremeKraj);
			
			if(vremePocetak >= pvremePocetak && vremePocetak<=pvremeKraj) {
				System.out.println("USO JE 1");
				throw new Exception("Patient already has an appointment at that time.");
				// kako throw exception
			}
			if(vremeKraj >= pvremePocetak && vremeKraj<=pvremeKraj) {
				System.out.println("USO JE 2");
				throw new Exception("Patient already has an appointment at that time.");
				// kako throw exception
			}
		}
		
		selectedAppointment.setPatient(patient);
		dermatologistAppointmentRepository.save(selectedAppointment);

		userNotificationService.sendReservationConfirmation(patient.getEmail(), "dermatologist");
		
		wantedAppontments = findAvailable(drugstoreId);
		return wantedAppontments;
	}
	
	public DermatologistAppointment findAppointment(String dermatologistAppointmentId) {
		DermatologistAppointment da = dermatologistAppointmentRepository.findById(dermatologistAppointmentId).orElse(null);
		return da;
	}
	
	public List<DermatologistAppointment> returnAppointments(String patientId, Pageable pageable) {
		return dermatologistAppointmentRepository.findByPatientId(patientId,pageable);
	}
	
	public int returnAppointmentsLength(String patientId) {
		return dermatologistAppointmentRepository.findByPatientId(patientId).size();
	}
	
	public Integer reservationsLength(String patientId) {
		return dermatologistAppointmentRepository.findByPatientId(patientId).size();
	}
	
	public List<DermatologistAppointment> findAvailable(String drugstoreId, String dermatologistId) {
		List<DermatologistAppointment> allAppointments = findAll();
		List<DermatologistAppointment> wantedAppontments = new ArrayList<>();
		String str = ""; 
		for(DermatologistAppointment appointment : allAppointments) {
			if(appointment.getDrugstore().getId().equals(drugstoreId) && (appointment.getPatient() == null) && (appointment.getDermatologist().getId().equals(dermatologistId)))
				wantedAppontments.add(appointment);
		}
		System.out.println(wantedAppontments);
		return wantedAppontments;
	}

	public List<DermatologistAppointment> reserveAppointment(String drugstoreId, String patientId, String appointmentId) {
		List<DermatologistAppointment> allAppointments = findAll();
		
		for(DermatologistAppointment appointment : allAppointments) {
			if(appointment.getId().equals(appointmentId)) {
				Patient patient = patientRepository.findById(patientId).orElse(null);;
				appointment.setPatient(patient);
				dermatologistAppointmentRepository.save(appointment);
			}

		}
		
		return allAppointments;
	}
	
	public List<DermatologistAppointment> findAllDermatologistAppointments(String dermatologistId) {
		List<DermatologistAppointment> allAppointments = findAll();
		List<DermatologistAppointment> wantedAppontments = new ArrayList<>();
		String str = ""; 
		for(DermatologistAppointment appointment : allAppointments) {
			if(appointment.getDermatologist().getId().equals(dermatologistId))
				wantedAppontments.add(appointment);
		}
		System.out.println(wantedAppontments);
		return wantedAppontments;
	}

	public DermatologistAppointment endAppointment(String appointmentId, String appointmentReport) {
		DermatologistAppointment da = dermatologistAppointmentRepository.findById(appointmentId).orElse(null);
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
		dermatologistAppointmentRepository.save(da);
		patientCategoryService.updatePatientCategoryFromAppointment(da.getPatient(), "dermatologist");
		return da;
	}

	public List<DermatologistAppointment> findAllDermatologistAppointmentsDone(String dermatologistId, Pageable pageable) {
		
		return dermatologistAppointmentRepository.findByDermatologistIdAndProcessedTrue(dermatologistId, pageable);
	}

	public List<DermatologistAppointment> cancelAppointment(String appointmentId) {
		DermatologistAppointment da = dermatologistAppointmentRepository.findById(appointmentId).orElse(null);
		String patientId = da.getPatient().getId();
		da.setPatient(null);
		dermatologistAppointmentRepository.save(da);
		return dermatologistAppointmentRepository.findByPatientId(patientId);
	}

	public int findAllDermatologistAppointmentsDoneLength(String dermatologistId) {
		return dermatologistAppointmentRepository.findByDermatologistIdAndProcessedTrue(dermatologistId).size();
	}

	public List<DermatologistAppointment> findAllDermatologistAppointmentsTodo(String dermatologistId, Pageable pageable) {
		return dermatologistAppointmentRepository.findByDermatologistIdAndProcessedFalseAndPatientNotNull(dermatologistId,pageable);
	}

	public int findAllDermatologistAppointmentsTodoLength(String dermatologistId) {
		return dermatologistAppointmentRepository.findByDermatologistIdAndProcessedFalseAndPatientNotNull(dermatologistId).size();
	}

	public Dermatologist findById(String dermatologistId) {
		// TODO Auto-generated method stub
		return dermatologistRepository.findById(dermatologistId).orElse(null);
	}
	
}
