package pharmacyhub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.Drug;
import pharmacyhub.domain.Ingredient;
import pharmacyhub.repositories.DrugRepository;
import pharmacyhub.repositories.IngredientRepository;

@Service
public class DrugService {

	@Autowired
	private DrugRepository drugRepository;

	@Autowired
	private IngredientRepository ingredientRepository;

	public List<Drug> findAll() {
		return drugRepository.findAll();
	}

	public Drug save(Drug drug) throws Exception {

		if (drug.getIngredients().isEmpty()) {
			throw new Exception("Empty ingredients list!");
		}

		if (!areIngredientsValid(drug.getIngredients())) {
			throw new Exception("This ingrediant does not exist");
		}

		return drugRepository.save(drug);
	}

	private boolean areIngredientsValid(List<Ingredient> sentIngredients) throws Exception {
		for (Ingredient ingredient : sentIngredients) {
			Optional<Ingredient> ingredientEntity = ingredientRepository.findById(ingredient.getId());
			if (!ingredientEntity.isPresent()) {
				return false;
			}
		}

		return true;
	}

}
