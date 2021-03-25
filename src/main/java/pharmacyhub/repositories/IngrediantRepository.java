package pharmacyhub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pharmacyhub.domain.Ingrediant;

public interface IngrediantRepository extends JpaRepository<Ingrediant, String>{
	
}
