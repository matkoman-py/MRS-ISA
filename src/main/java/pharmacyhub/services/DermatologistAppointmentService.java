package pharmacyhub.services;

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
	
	public DermatologistAppointment save(DermatologistAppointmentDto dermatologistAppointmentDto) throws Exception {
		//treba provera da li je dermatolog u datom periodu slobodan
		return dermatologistAppointmentRepository.save(new DermatologistAppointment(dermatologistAppointmentDto.getDermatologist(), drugstoreRepository.findById(dermatologistAppointmentDto.getDrugstoreId()).orElse(null), dermatologistAppointmentDto.getDate(), dermatologistAppointmentDto.getTime(), dermatologistAppointmentDto.getDuration(), null, null));
	}
	
}
