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

	public List<Ingredient> findAll() {
		return ingrediantRepository.findAll();
	}
	
	public Ingredient findById(String id) {
		return ingrediantRepository.findById(id).orElse(null);
	}

}
