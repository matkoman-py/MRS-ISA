package pharmacyhub.repositories;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import pharmacyhub.domain.Drug;
import pharmacyhub.domain.users.Patient;

@Repository
public class PatientRepository {
	private ArrayList<Patient> patients;
	
	public PatientRepository() {
		patients = new ArrayList<Patient>();
		this.fill();
	}
	
	public ArrayList<Patient> readAll() {
		return patients;
	}
	
	public void fill() {
		Patient pat1 = new Patient();
		pat1.setName("Marko");
		pat1.setSurname("Markovic");
		patients.add(pat1);
		Patient pat2 = new Patient();
		pat2.setName("Mirko");
		pat2.setSurname("Mirkovic");
		patients.add(pat2);
		Patient pat3 = new Patient();
		pat3.setName("Petar");
		pat3.setSurname("Markovic");
		patients.add(pat3);
	}
}
