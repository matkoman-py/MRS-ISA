package pharmacyhub.repositories;

import java.util.List;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.complaints.Complaint;
import pharmacyhub.domain.enums.ComplaintType;
import pharmacyhub.domain.users.User;

public interface ComplaintRepository extends JpaRepository<Complaint, String> {
	List<Complaint> findByDrugstore(Drugstore drugstore);
	List<Complaint> findByEmployee(User employee);
	List<Complaint> findByPatientId(String patientId, Pageable pageable);
	List<Complaint> findByType(ComplaintType type);
	
	@Transactional
	@Lock(LockModeType.OPTIMISTIC)
	Complaint findByIdAndHasReplyFalse(String id);
}
