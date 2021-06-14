package pharmacyhub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pharmacyhub.domain.AbsenceRequest;
import pharmacyhub.domain.enums.AbsenceRequestStatus;
import pharmacyhub.domain.users.User;

public interface AbsenceRequestRepository extends JpaRepository<AbsenceRequest, String>{
	
	List<AbsenceRequest> findByEmployeeAndStatus(User user, AbsenceRequestStatus status);
	
	AbsenceRequest findByEmployeeIdAndStatus(String id, AbsenceRequestStatus status);
	
}
