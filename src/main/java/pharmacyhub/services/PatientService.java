package pharmacyhub.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.users.Patient;
import pharmacyhub.repositories.users.PatientRepository;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;
	
	public List<Patient> findAll()
	{
		return patientRepository.findAll();
	}
	
	public List<Patient> returnPatients(String patientName,String patientSurname) {
		
		List<Patient> allPatients = findAll();
		List<Patient> wantedPatients = new ArrayList<>();
		
		for(Patient pat:allPatients) {
			if( (pat.getName().toLowerCase().contains(patientName.toLowerCase()) || patientName.equals("0")) && 
				(pat.getSurname().toLowerCase().contains(patientSurname.toLowerCase()) || patientSurname.equals("0"))) {
				wantedPatients.add(pat);
			}
		}
		
		return wantedPatients;
	}

}
