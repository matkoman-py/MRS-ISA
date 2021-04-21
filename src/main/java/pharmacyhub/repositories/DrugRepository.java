package pharmacyhub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import pharmacyhub.domain.Drug;

public interface DrugRepository extends JpaRepository<Drug, String>, JpaSpecificationExecutor<Drug>{
	Drug findByName(String name);
}
