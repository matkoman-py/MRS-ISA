package pharmacyhub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public List<Patient> fill() {
		
		Patient pat1 = new Patient();
		pat1.setName("Marko");
		pat1.setSurname("Markovic");
		pat1.setEmail("marko@markovic.com");
		pat1.setPassword("pass");
		patientRepository.save(pat1);
		
		Patient pat2 = new Patient();
		pat2.setName("Mirko");
		pat2.setSurname("Mirkovic");
		pat2.setEmail("mirko@mirkovic.com");
		pat2.setPassword("pass");
		patientRepository.save(pat2);
		
		Patient pat3 = new Patient();
		pat3.setName("Petar");
		pat3.setSurname("Markovic");
		pat3.setEmail("petar@markovic.com");
		pat3.setPassword("pass");
		patientRepository.save(pat3);
		
		return patientRepository.findAll();
	}
}
