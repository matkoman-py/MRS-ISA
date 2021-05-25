package pharmacyhub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.complaints.Complaint;
import pharmacyhub.domain.enums.ComplaintType;
import pharmacyhub.domain.users.Patient;
import pharmacyhub.domain.users.User;

public interface ComplaintRepository extends JpaRepository<Complaint, String> {
	List<Complaint> findByDrugstore(Drugstore drugstore);
	List<Complaint> findByEmployee(User employee);
	List<Complaint> findByPatient(Patient patinet);
	List<Complaint> findByType(ComplaintType type);
}
