package pharmacyhub.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.DermatologistAppointment;
import pharmacyhub.dto.DermatologistAppointmentDto;
import pharmacyhub.repositories.DermatologistAppointmentRepository;
import pharmacyhub.repositories.DrugstoreRepository;

@Service
public class DermatologistAppointmentService {
	
	@Autowired 
	private DermatologistAppointmentRepository dermatologistAppointmentRepository;
	
	@Autowired 
	private DrugstoreRepository drugstoreRepository;
	
	public List<DermatologistAppointment> findAll(){
		return dermatologistAppointmentRepository.findAll();
	}
	
	public DermatologistAppointment save(DermatologistAppointmentDto dermatologistAppointmentDto) throws Exception {
		//treba provera da li je dermatolog u datom periodu slobodan
		return dermatologistAppointmentRepository.save(new DermatologistAppointment(dermatologistAppointmentDto.getDermatologist(), drugstoreRepository.findById(dermatologistAppointmentDto.getDrugstoreId()).orElse(null), dermatologistAppointmentDto.getDate(), dermatologistAppointmentDto.getTime(), dermatologistAppointmentDto.getDuration(), null, null));
	}

	//List<DermatologistAppointment>
	public List<DermatologistAppointment> findAvailable(String drugstoreId) {
		List<DermatologistAppointment> allAppointments = findAll();
		List<DermatologistAppointment> wantedAppontments = new ArrayList<>();
		String str = ""; 
		for(DermatologistAppointment appointment : allAppointments) {
			str += appointment.getDrugstore().getId() + "    " +  drugstoreId + "\n";
			if(appointment.getDrugstore().getId().equals(drugstoreId))
				wantedAppontments.add(appointment);
		}
		return wantedAppontments;
	}
	
}





