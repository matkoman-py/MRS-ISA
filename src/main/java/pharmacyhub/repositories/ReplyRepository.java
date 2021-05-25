package pharmacyhub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pharmacyhub.domain.complaints.Complaint;
import pharmacyhub.domain.complaints.Reply;

public interface ReplyRepository extends JpaRepository<Reply, String> {
	Reply findByComplaint(Complaint complaint);
}
