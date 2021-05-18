package pharmacyhub.repositories.users;

import java.util.List;

import pharmacyhub.domain.users.Patient;

public interface PatientRepository extends AbstractUserRepository<Patient> {
	List<Patient> findByName(String name);
	Patient findByEmail(String email);
	Patient getById(String patientId);
}
