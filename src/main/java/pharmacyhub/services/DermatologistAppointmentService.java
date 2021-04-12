package pharmacyhub.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.DermatologistAppointment;
import pharmacyhub.domain.users.Patient;
import pharmacyhub.dto.DermatologistAppointmentDto;
import pharmacyhub.dto.DermatologistAppointmentPatientDto;
import pharmacyhub.repositories.DermatologistAppointmentRepository;
import pharmacyhub.repositories.DrugstoreRepository;
import pharmacyhub.repositories.users.DermatologistRepository;
import pharmacyhub.repositories.users.PatientRepository;

@Service
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

	
	public List<DermatologistAppointment> findAll(){
		return dermatologistAppointmentRepository.findAll();
	}
	
	public DermatologistAppointment save(DermatologistAppointmentDto dermatologistAppointmentDto) throws Exception {
		//treba provera da li je dermatolog u datom periodu slobodan
		return dermatologistAppointmentRepository.save(new DermatologistAppointment(dermatologistAppointmentDto.getDermatologist(), drugstoreRepository.findById(dermatologistAppointmentDto.getDrugstoreId()).orElse(null), dermatologistAppointmentDto.getDate(), dermatologistAppointmentDto.getTime(), dermatologistAppointmentDto.getDuration(), null, null, dermatologistAppointmentDto.getPrice()));
	}
	
	public DermatologistAppointment saveWithPatient(DermatologistAppointmentPatientDto dermatologistAppointmentPatientDto) throws Exception {
		//treba provera da li je dermatolog u datom periodu slobodan
		return dermatologistAppointmentRepository.save(new DermatologistAppointment(dermatologistRepository.findById(dermatologistAppointmentPatientDto.getDermatologistId()).orElse(null), drugstoreRepository.findById(dermatologistAppointmentPatientDto.getDrugstoreId()).orElse(null), dermatologistAppointmentPatientDto.getDate(), dermatologistAppointmentPatientDto.getTime(), dermatologistAppointmentPatientDto.getDuration(), patientRepository.findById(dermatologistAppointmentPatientDto.getPatientId()).orElse(null), null));
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

	public List<DermatologistAppointment> createReservation(String patientId,String appointmentId,String drugstoreId) throws MessagingException {
		List<DermatologistAppointment> allAppointments = findAll();
		List<DermatologistAppointment> wantedAppontments = new ArrayList<>();
		
		Patient patient = new Patient();
		for(DermatologistAppointment appointment : allAppointments) {
			//str += appointment.getDrugstore().getId() + "    " +  drugstoreId + "\n";
			if(appointment.getId().equals(appointmentId)) {
				patient = patientRepository.findById(patientId).orElse(null);;
				appointment.setPatient(patient);
				dermatologistAppointmentRepository.save(appointment);
			}

		}
		userNotificationService.sendReservationConfirmation(patient.getEmail());
		
		wantedAppontments = findAvailable(drugstoreId);
		return wantedAppontments;
	}
	
	public DermatologistAppointment findAppointment(String dermatologistAppointmentId) {
		DermatologistAppointment da = dermatologistAppointmentRepository.findById(dermatologistAppointmentId).orElse(null);
		return da;
	}
	
	public List<DermatologistAppointment> findAvailable(String drugstoreId, String dermatologistId) {
		List<DermatologistAppointment> allAppointments = findAll();
		List<DermatologistAppointment> wantedAppontments = new ArrayList<>();
		String str = ""; 
		System.out.println(drugstoreId);
		System.out.println(dermatologistId);
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
}





