package pharmacyhub.repositories.users;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import pharmacyhub.domain.Drug;
import pharmacyhub.domain.users.Patient;

public interface PatientRepository extends AbstractUserRepository<Patient> {
	List<Patient> findByName(String name);
	Patient findByEmail(String email);
	Patient getById(String patientId);
	//Patient findById(String patientId, Pageable pageable);
}
