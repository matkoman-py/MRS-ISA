package pharmacyhub.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.Drug;
import pharmacyhub.domain.DrugType;
import pharmacyhub.domain.Ingredient;
import pharmacyhub.domain.Manufacturer;
import pharmacyhub.repositories.DrugRepository;
import pharmacyhub.repositories.DrugTypeRepository;
import pharmacyhub.repositories.IngredientRepository;
import pharmacyhub.repositories.ManufacturerRepository;

@Service
public class DrugService{
	
	@Autowired
	private DrugRepository drugRepository;
	
	@Autowired
	private DrugTypeRepository drugTypeRepository;
	
	@Autowired
	private IngredientRepository ingrediantRepository;
	
	@Autowired
	private ManufacturerRepository manufacturerRepository;
		
	public void seedDrugs() {
		DrugType drugType = new DrugType();
		Manufacturer manufacturer = new Manufacturer();
		Ingredient ingredient = new Ingredient();
		ArrayList<Ingredient> ingrediants = new ArrayList<>();
		
		ingrediants.add(ingredient);
		drugRepository.add(new Drug("id1", "Gentamicin", "cream", true, drugTypeRepository.read("id1"), 
				manufacturerRepository.read("id1"), null, ingrediantRepository.findAll(), "mast 0,1%, 15g", 10));
	}

	
	public ArrayList<Drug> readAll()
	{
		return drugRepository.readAll();
	}
	
	public Drug add(Drug drug)
	{
		return drugRepository.add(drug);
	}

}
