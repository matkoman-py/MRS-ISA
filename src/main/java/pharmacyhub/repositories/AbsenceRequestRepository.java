package pharmacyhub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pharmacyhub.domain.AbsenceRequest;
import pharmacyhub.domain.Drugstore;

public interface AbsenceRequestRepository extends JpaRepository<AbsenceRequest, String>{

}
