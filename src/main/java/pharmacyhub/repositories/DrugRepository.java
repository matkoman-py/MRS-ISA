package pharmacyhub.repositories;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import pharmacyhub.domain.Drug;

public interface DrugRepository extends JpaRepository<Drug, String>, JpaSpecificationExecutor<Drug>{
	
	Optional<Drug> findById(String id);
	
	Drug findByName(String name);

	@Transactional
	Drug findOneById(String id);

	@Transactional
	@Lock(LockModeType.PESSIMISTIC_READ)
	@Query("select d from Drug d where d.id = ?1")
	Drug findByIdWithLock(String drugId);
	
	@Transactional
	void deleteById(String id);
}
