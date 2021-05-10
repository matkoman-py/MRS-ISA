package pharmacyhub.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import pharmacyhub.domain.DrugRequest;


public interface DrugRequestRepository extends JpaRepository<DrugRequest, String>{
	

}
