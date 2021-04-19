package pharmacyhub.repositories.users;

import java.util.List;

import pharmacyhub.domain.users.Patient;

public interface PatientRepository extends AbstractUserRepository<Patient> {
	List<Patient> findByName(String name);

	Patient getById(String patientId);
}
