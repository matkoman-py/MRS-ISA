package pharmacyhub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pharmacyhub.domain.Drug;

public interface DrugRepository extends JpaRepository<Drug, String>{
	
}
