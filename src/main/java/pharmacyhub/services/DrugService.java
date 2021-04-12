package pharmacyhub.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.Drug;
import pharmacyhub.domain.DrugStock;
import pharmacyhub.domain.Ingredient;
import pharmacyhub.dto.DrugInDrugstoreDto;
import pharmacyhub.repositories.DrugRepository;
import pharmacyhub.repositories.DrugStockRepository;
import pharmacyhub.repositories.DrugstoreRepository;
import pharmacyhub.repositories.DrugPriceRepository;
import pharmacyhub.repositories.IngredientRepository;

@Service
public class DrugService {

	@Autowired
	private DrugRepository drugRepository;

	@Autowired
	private IngredientRepository ingredientRepository;
	
	@Autowired
	private DrugStockRepository drugStockRepository;
	
	@Autowired
	private DrugstoreRepository drugstoreRepository;
	
	@Autowired
	private DrugPriceRepository drugPriceRepository;

	public List<Drug> findAll() {
		return drugRepository.findAll();
	}
	
	public Collection<Drug> returnDrugs(String drugName,String drugType,String drugForm,String drugManufacturer,String drugReceipt){
		Collection<Drug> allDrugs = findAll();
		Collection<Drug> wantedDrugs = new ArrayList<>();
		for(Drug medicine:allDrugs) {
			if((medicine.getName().toLowerCase().contains(drugName.toLowerCase()) || drugName.equals("0")) &&
			   (medicine.getType().getName().equals(drugType) || drugType.equals("0")) &&
			   (medicine.getForm().toLowerCase().contains(drugForm) || drugForm.equals("0")) &&
			   (medicine.getManufacturer().getName().equals(drugManufacturer) || drugManufacturer.equals("0")) &&
			   ( ((medicine.isReceipt() && drugReceipt.equals("Yes")) || (!medicine.isReceipt() && drugReceipt.equals("No")))  || drugReceipt.equals("0"))) {
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

	public List<DrugInDrugstoreDto> getDrugsInDrugstore(String drugstoreId) {
		List<DrugStock> drugsOnStock = drugStockRepository.findByDrugstore(drugstoreRepository.findById(drugstoreId).orElse(null));
		List<DrugInDrugstoreDto> drugsInDrugstore = new ArrayList<DrugInDrugstoreDto>();
		for (DrugStock ds : drugsOnStock) {
			drugsInDrugstore.add(new DrugInDrugstoreDto(ds.getDrug().getName(), ds.getDrug().getForm(), ds.getDrug().isReceipt(), ds.getDrug().getType(), ds.getDrug().getManufacturer(), ds.getAmount()));
		}
		return drugsInDrugstore;
  }

 public boolean delete(String id) throws Exception {
		Drug drug = drugRepository.findById(id).orElse(null);
		if(drug == null) {
			throw new Exception("No such drug");
		}
		
		drugPriceRepository.deleteByDrug(drug);
		drugStockRepository.deleteByDrug(drug);		
		drugRepository.deleteById(id);
		
		return true;
	}

	public Drug update(Drug drug) throws Exception {
		// TODO Auto-generated method stub
		if (drugRepository.findById(drug.getId()) == null) {
			throw new Exception("Drug doesn't exists");
		}
		return save(drug);
	}

}
