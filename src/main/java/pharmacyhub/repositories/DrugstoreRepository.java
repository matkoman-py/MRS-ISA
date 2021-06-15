package pharmacyhub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import pharmacyhub.domain.Drugstore;

public interface DrugstoreRepository extends JpaRepository<Drugstore, String>, JpaSpecificationExecutor<Drugstore>{
	@Transactional
	Drugstore findOneById(String id);
	
	@Transactional
	void delete(Drugstore drugstore);
	
	List<Drugstore> findByName(String name);
}