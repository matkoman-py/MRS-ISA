package pharmacyhub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import pharmacyhub.domain.Drugstore;

public interface DrugstoreRepository extends JpaRepository<Drugstore, String>, JpaSpecificationExecutor<Drugstore>{
	List<Drugstore> findByName(String name);
}