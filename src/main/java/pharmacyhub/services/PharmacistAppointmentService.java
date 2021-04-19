package pharmacyhub.services;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pharmacyhub.domain.DermatologistAppointment;
import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.PharmacistAppointment;
import pharmacyhub.domain.users.Pharmacist;
import pharmacyhub.dto.DermatologistAppointmentPatientDto;
import pharmacyhub.dto.PharmacistAppointmentPatientDto;
import pharmacyhub.repositories.DermatologistAppointmentRepository;
import pharmacyhub.repositories.DrugstoreRepository;
import pharmacyhub.repositories.PharmacistAppointmentRepository;
import pharmacyhub.repositories.users.DermatologistRepository;
import pharmacyhub.repositories.users.PatientRepository;
import pharmacyhub.repositories.users.PharmacistRepository;

@Service
public class PharmacistAppointmentService {
	@Autowired 
	private PharmacistAppointmentRepository pharmacistAppointmentRepository;
	
	@Autowired 
	private DrugstoreRepository drugstoreRepository;
	
	@Autowired 
	private PatientRepository patientRepository;
	
	@Autowired
	private PharmacistRepository pharmacistRepository;
	
    @Autowired
	private UserNotificationService userNotificationService;
	
    public List<PharmacistAppointment> getAppointments(String patientId) throws MessagingException {
    	userNotificationService.sendReservationConfirmation(patientRepository.getById(patientId).getEmail());
		return pharmacistAppointmentRepository.findByPatientId(patientId);
	}
	public PharmacistAppointment saveWithPatient(PharmacistAppointmentPatientDto pharmacistAppointmentPatientDto) throws Exception {
		//treba provera da li je dermatolog u datom periodu slobodan
		return pharmacistAppointmentRepository.save(new PharmacistAppointment(pharmacistRepository.findById(pharmacistAppointmentPatientDto.getPharmacistId()).orElse(null),pharmacistAppointmentPatientDto.getDate(), pharmacistAppointmentPatientDto.getTime(), pharmacistAppointmentPatientDto.getDuration(), patientRepository.findById(pharmacistAppointmentPatientDto.getPatientId()).orElse(null), null));
	}
	
	public PharmacistAppointment findAppointment(String pharmacistAppointmentId) {
		PharmacistAppointment pa = pharmacistAppointmentRepository.findById(pharmacistAppointmentId).orElse(null);
		return pa;
	}

	public List<Drugstore> findDrugstores(String pharmacistAppointmentTime, String pharmacistAppointmentDate) {
		List<Pharmacist> allPharmacists = pharmacistRepository.findAll();
		List<Drugstore>  wantedDrugstores = new ArrayList<>();
		
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
					wantedDrugstores.add(drugstoreRepository.findById(ph.getDrugstore().getId()).orElse(null));
				}
			}
		}
		return wantedDrugstores;
	}

	public List<Pharmacist> findPharmacists(String drugstoreId,String pharmacistAppointmentDate,String pharmacistAppointmentTime) {
		List<Pharmacist> allPharmacists =  pharmacistRepository.findByDrugstore(drugstoreRepository.findById(drugstoreId).orElse(null));
		List<Pharmacist> wantedPharmacist = new ArrayList<>();
		
		pharmacistAppointmentRepository.findAll();
		Time in = new Time(Integer.parseInt(pharmacistAppointmentTime.substring(0,2)),Integer.parseInt(pharmacistAppointmentTime.substring(3,5)),0);
		//String hours = pharmacistAppointmentTime.substring(0,2);
		//int inputTime = Integer.parseInt(hours) * 3600;
		//String minutes = pharmacistAppointmentTime.substring(3,5);
		//inputTime += Integer.parseInt(minutes) * 60;
		long inputTime = in.getTime();
		
		for(Pharmacist ph:allPharmacists) {
			boolean free = true;
			List<PharmacistAppointment> Appointments = pharmacistAppointmentRepository.findByPharmacistId(ph.getId());
			for(PharmacistAppointment Appointment:Appointments) {
				if(Appointment.getDate().toString().contains(pharmacistAppointmentDate)) {
					long busyFrom = Appointment.getTime().getTime();

					long busyTo = busyFrom + Appointment.getDuration()*60000;
					
					System.out.println(inputTime);
					System.out.println(busyFrom);
					System.out.println(busyTo);
					if(inputTime >= busyFrom && inputTime <= busyTo) {
						free = false;
					}
				}
			}
			if(free == true) wantedPharmacist.add(ph);
		}
		return wantedPharmacist; 
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
}
