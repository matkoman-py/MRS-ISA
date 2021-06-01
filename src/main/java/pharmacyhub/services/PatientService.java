package pharmacyhub.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.users.Patient;
import pharmacyhub.repositories.specifications.drugs.DrugSpecifications;
import pharmacyhub.repositories.users.PatientRepository;
import specifications.patient.PatientSpecifications;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;
	
	public List<Patient> findAll(Pageable pageable)
	{
		return patientRepository.findAll(pageable).toList();
	}
	
	public List<Patient> returnPatients(Pageable pageable,String patientName,String patientSurname) {
		
		/*List<Patient> allPatients = findAll();
		List<Patient> wantedPatients = new ArrayList<>();
		
		for(Patient pat:allPatients) {
			if( (pat.getName().toLowerCase().contains(patientName.toLowerCase()) || patientName.equals("0")) && 
				(pat.getSurname().toLowerCase().contains(patientSurname.toLowerCase()) || patientSurname.equals("0"))) {
				wantedPatients.add(pat);
			}
		}*/
		System.out.println(patientName + patientSurname);
		return patientRepository.findAll(PatientSpecifications.withSearch(patientName, patientSurname),pageable).toList();
	}
	
	public Patient update(Patient patient) throws Exception {

			Patient pat = (Patient) patientRepository.findById(patient.getId()).orElse(null);
			if (pat.equals(null)) {
				throw new Exception("This patient does not exist!");
			}
			if(patient.getEmail() != null) pat.setEmail(patient.getEmail());
			if(patient.getLocation() != null) pat.setLocation(patient.getLocation());
			if(patient.getName() != null) pat.setName(patient.getName());
			if(patient.getPassword() != null) pat.setPassword(patient.getPassword());
			if(patient.getPhoneNumber() != null) pat.setPhoneNumber(patient.getPhoneNumber());
			if(patient.getSurname() != null) pat.setSurname(patient.getSurname());
			if(patient.getAllergens() != null) pat.setAllergens(patient.getAllergens());

			patientRepository.save(pat);
			return pat;
	}

	public Patient returnPatient(String patientId) {
		return patientRepository.findById(patientId).orElse(null);
	}
	
	public Patient penalty(String patientId) {
		System.out.println(patientId+"KURAC");
		Patient pat = patientRepository.findById(patientId).orElse(null);
		pat.setPenaltyCounter(pat.getPenaltyCounter()+1);
		patientRepository.save(pat);
		return pat;
	}

	public Integer returnPatientsLength(String patientName, String patientSurname) {
		// TODO Auto-generated method stub
		return patientRepository.findAll(PatientSpecifications.withSearch(patientName, patientSurname)).size();
	}

}
