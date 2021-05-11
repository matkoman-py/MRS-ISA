package pharmacyhub.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.Drug;
import pharmacyhub.domain.DrugStock;
import pharmacyhub.domain.Ingredient;
import pharmacyhub.dto.DrugInDrugstoreDto;
import pharmacyhub.dto.search.DrugSearchDto;
import pharmacyhub.repositories.DrugPriceRepository;
import pharmacyhub.repositories.DrugRepository;
import pharmacyhub.repositories.DrugStockRepository;
import pharmacyhub.repositories.DrugstoreRepository;
import pharmacyhub.repositories.IngredientRepository;
import pharmacyhub.repositories.specifications.drugs.DrugSpecifications;

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

	public List<Drug> findAll(Pageable pageable) {
		return drugRepository.findAll(pageable).toList();
	}
	
	public Collection<Drug> returnDrugs(DrugSearchDto drugSearchDto, Pageable pageable){
		return drugRepository.findAll(DrugSpecifications.withSearch(drugSearchDto), pageable).toList();
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

	public List<DrugInDrugstoreDto> getDrugsInDrugstore(String drugstoreId, Pageable pageable) {
		List<DrugStock> drugsOnStock = drugStockRepository.findByDrugstore(drugstoreRepository.findById(drugstoreId).orElse(null), pageable);
		List<DrugInDrugstoreDto> drugsInDrugstore = new ArrayList<DrugInDrugstoreDto>();
		for (DrugStock ds : drugsOnStock) {
			drugsInDrugstore.add(new DrugInDrugstoreDto(ds.getDrug().getId(),ds.getDrug().getName(), ds.getDrug().getForm(), ds.getDrug().isReceipt(), ds.getDrug().getType(), ds.getDrug().getManufacturer(), ds.getAmount()));
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
