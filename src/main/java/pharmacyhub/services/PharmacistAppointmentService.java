package pharmacyhub.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.DermatologistAppointment;
import pharmacyhub.domain.PharmacistAppointment;
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
	
	public PharmacistAppointment saveWithPatient(PharmacistAppointmentPatientDto pharmacistAppointmentPatientDto) throws Exception {
		//treba provera da li je dermatolog u datom periodu slobodan
		return pharmacistAppointmentRepository.save(new PharmacistAppointment(pharmacistRepository.findById(pharmacistAppointmentPatientDto.getPharmacistId()).orElse(null),pharmacistAppointmentPatientDto.getDate(), pharmacistAppointmentPatientDto.getTime(), pharmacistAppointmentPatientDto.getDuration(), patientRepository.findById(pharmacistAppointmentPatientDto.getPatientId()).orElse(null), null));
	}
	
	public PharmacistAppointment findAppointment(String pharmacistAppointmentId) {
		PharmacistAppointment pa = pharmacistAppointmentRepository.findById(pharmacistAppointmentId).orElse(null);
		return pa;
	}
}
