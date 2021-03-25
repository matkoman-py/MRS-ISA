package pharmacyhub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.Ingredient;
import pharmacyhub.repositories.IngredientRepository;

@Service
public class IngredientService {

	@Autowired
	private IngredientRepository ingrediantRepository;
	
	public List<Ingredient> findAll()
	{
		return ingrediantRepository.findAll();
	}
	
	public List<Ingredient> seedIngrediants() {
		ingrediantRepository.save(new Ingredient("id1", "cellulose"));
		ingrediantRepository.save(new Ingredient("id2", "lactose monohydrate"));
		ingrediantRepository.save(new Ingredient("id3", "colloidal anhydrous silica"));
		ingrediantRepository.save(new Ingredient("id4", "magnesium stearate"));
		return findAll();
	}
	
}
