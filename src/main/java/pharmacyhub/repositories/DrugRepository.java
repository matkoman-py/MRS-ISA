package pharmacyhub.repositories;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.transaction.annotation.Transactional;

import pharmacyhub.domain.Drug;

public interface DrugRepository extends JpaRepository<Drug, String>, JpaSpecificationExecutor<Drug>{
	
	@Transactional
	@Lock(LockModeType.PESSIMISTIC_READ)
	Optional<Drug> findById(String id);
	
	Drug findByName(String name);
}
