package pharmacyhub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pharmacyhub.domain.Drugstore;

public interface DrugstoreRepository extends JpaRepository<Drugstore, String>{
	List<Drugstore> findByName(String name);
}