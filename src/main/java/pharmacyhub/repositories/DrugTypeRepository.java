package pharmacyhub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pharmacyhub.domain.DrugType;

public interface DrugTypeRepository extends JpaRepository<DrugType, String>{
	
}