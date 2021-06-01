package pharmacyhub.repositories;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import pharmacyhub.domain.complaints.Reply;

public interface ReplyRepository extends JpaRepository<Reply, String> {
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Reply findByComplaintId(String complaintId);
}
