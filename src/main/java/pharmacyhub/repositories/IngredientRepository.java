package pharmacyhub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pharmacyhub.domain.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, String>{
	
}
