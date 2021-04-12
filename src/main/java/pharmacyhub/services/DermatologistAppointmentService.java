package pharmacyhub.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.DermatologistAppointment;
import pharmacyhub.domain.users.Patient;
import pharmacyhub.dto.DermatologistAppointmentDto;
import pharmacyhub.repositories.DermatologistAppointmentRepository;
import pharmacyhub.repositories.DrugstoreRepository;
import pharmacyhub.repositories.users.PatientRepository;

@Service
public class DermatologistAppointmentService {
	
	@Autowired 
	private DermatologistAppointmentRepository dermatologistAppointmentRepository;
	
	@Autowired 
	private DrugstoreRepository drugstoreRepository;
	
	@Autowired 
	private PatientRepository patientRepository;
	
	public List<DermatologistAppointment> findAll(){
		return dermatologistAppointmentRepository.findAll();
	}
	
	public DermatologistAppointment save(DermatologistAppointmentDto dermatologistAppointmentDto) throws Exception {
		//treba provera da li je dermatolog u datom periodu slobodan
		return dermatologistAppointmentRepository.save(new DermatologistAppointment(dermatologistAppointmentDto.getDermatologist(), drugstoreRepository.findById(dermatologistAppointmentDto.getDrugstoreId()).orElse(null), dermatologistAppointmentDto.getDate(), dermatologistAppointmentDto.getTime(), dermatologistAppointmentDto.getDuration(), null, null, dermatologistAppointmentDto.getPrice()));
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

	public List<DermatologistAppointment> createReservation(String patientId,String drugstoreId,String DRUGSTOREID) {
		List<DermatologistAppointment> allAppointments = findAll();
		List<DermatologistAppointment> wantedAppontments = new ArrayList<>();
		
		for(DermatologistAppointment appointment : allAppointments) {
			//str += appointment.getDrugstore().getId() + "    " +  drugstoreId + "\n";
			if(appointment.getId().equals(drugstoreId)) {
				Patient patient = patientRepository.findById(patientId).orElse(null);;
				appointment.setPatient(patient);
				dermatologistAppointmentRepository.save(appointment);
			}

		}
		wantedAppontments = findAvailable(DRUGSTOREID);
		return wantedAppontments;
	}
	
}





