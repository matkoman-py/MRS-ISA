package pharmacyhub.services;

import java.util.ArrayList;
import java.util.Collection;
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
	
	public Collection<Drug> returnDrugs(String drugName,String drugType,String drugForm,String drugManufacturer,String drugReceipt){
		Collection<Drug> allDrugs = findAll();
		Collection<Drug> wantedDrugs = new ArrayList<>();
		//String sta = "";
		for(Drug medicine:allDrugs) {
			//sta += " " + medicine.getType().getName() + " " + drugType + "x \n";
			if((medicine.getName().equals(drugName) || drugName.equals("0")) &&
			   (medicine.getType().getName().equals(drugType) || drugType.equals("0")) &&
			   (medicine.getForm().equals(drugForm) || drugForm.equals("0")) &&
			   (medicine.getManufacturer().getName().equals(drugManufacturer) || drugManufacturer.equals("0")) &&
			   ( ((medicine.isReceipt() && drugReceipt.equals("Yes")) || (!medicine.isReceipt() && drugReceipt.equals("No")))  || drugReceipt.equals("0"))) {
				//sta += " uso i ovde";
				wantedDrugs.add(medicine);
			}
		}
		return wantedDrugs;
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
