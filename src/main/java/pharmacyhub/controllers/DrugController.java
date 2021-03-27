package pharmacyhub.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import pharmacyhub.domain.Drug;
import pharmacyhub.domain.Ingredient;
import pharmacyhub.services.DrugService;
import pharmacyhub.services.IngredientService;

@Controller
@RequestMapping("/drugs")
public class DrugController {

	@Autowired
	private DrugService drugService;

	@Autowired
	private IngredientService ingredientService;

	@GetMapping(path = "/seed", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Drug>> seedDrugs() {
		return new ResponseEntity<>(drugService.findAll(), HttpStatus.OK);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Drug>> getDrugs() {
		return new ResponseEntity<>(drugService.findAll(), HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Drug> add(@RequestBody Drug drug) throws Exception {
				
		if (drug.getIngredients().isEmpty()) {
			throw new Exception("Empty ingredients list!");
		}

		List<Ingredient> ingredients = getIngredients(drug.getIngredients());
		drug.setIngredients(ingredients);

		return new ResponseEntity<>(drugService.save(drug), HttpStatus.OK);
	}

	private List<Ingredient> getIngredients(List<Ingredient> sentIngredients) throws Exception {
		List<Ingredient> ingredientEntities = new ArrayList<>();
		for (Ingredient ingredient : sentIngredients) {
			Ingredient ingredientEntity = ingredientService.findById(ingredient.getId());
			if (ingredientEntity == null) {
				throw new Exception("This ingrediant does not exist");
			}
			ingredientEntities.add(ingredientEntity);
		}
		return ingredientEntities;
	}
}
