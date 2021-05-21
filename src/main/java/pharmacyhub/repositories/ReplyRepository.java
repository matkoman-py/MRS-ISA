package pharmacyhub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pharmacyhub.domain.complaints.Complaint;
import pharmacyhub.domain.complaints.Reply;

public interface ReplyRepository extends JpaRepository<Reply, String> {
	List<Reply> findByComplaint(Complaint complaint);
}
