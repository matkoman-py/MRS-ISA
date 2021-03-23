package pharmacyhub.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.Drug;
import pharmacyhub.domain.users.Patient;
import pharmacyhub.repositories.DrugRepository;
import pharmacyhub.repositories.PatientRepository;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;
	
	public ArrayList<Patient> readAll()
	{
		return patientRepository.readAll();
	}
}
