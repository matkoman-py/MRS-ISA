package pharmacyhub.repositories.users;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import pharmacyhub.domain.Drug;
import pharmacyhub.domain.users.Patient;

public interface PatientRepository extends AbstractUserRepository<Patient>, JpaSpecificationExecutor<Patient> {
	List<Patient> findByName(String name);
	Patient findByEmail(String email);
	Patient getById(String patientId);
}
