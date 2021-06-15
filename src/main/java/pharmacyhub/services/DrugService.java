package pharmacyhub.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pharmacyhub.domain.Drug;
import pharmacyhub.domain.DrugOrder;
import pharmacyhub.domain.DrugRequest;
import pharmacyhub.domain.DrugStock;
import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.Ingredient;
import pharmacyhub.domain.OrderStock;
import pharmacyhub.domain.enums.OrderStatus;
import pharmacyhub.domain.users.Patient;
import pharmacyhub.dto.CreateDrugOrderDto;
import pharmacyhub.dto.DrugDto;
import pharmacyhub.dto.DrugInDrugstoreDto;
import pharmacyhub.dto.search.DrugSearchDto;
import pharmacyhub.repositories.DrugOrderRepository;
import pharmacyhub.repositories.DrugPriceRepository;
import pharmacyhub.repositories.DrugRepository;
import pharmacyhub.repositories.DrugRequestRepository;
import pharmacyhub.repositories.DrugStockRepository;
import pharmacyhub.repositories.DrugstoreRepository;
import pharmacyhub.repositories.IngredientRepository;
import pharmacyhub.repositories.specifications.drugs.DrugSpecifications;
import pharmacyhub.repositories.users.PatientRepository;

@Service
public class DrugService {

	@Autowired
	private PatientRepository patientRepository;
	
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
	
	@Autowired
	private DrugRequestRepository drugRequestRepository;
	
	@Autowired
	private DrugOrderRepository drugOrderRepository;

	public List<Drug> findAll(Pageable pageable) {
		return drugRepository.findAll(pageable).toList();
	}
	
	public Collection<Drug> returnDrugs(DrugSearchDto drugSearchDto, Pageable pageable){
		return drugRepository.findAll(DrugSpecifications.withSearch(drugSearchDto), pageable).toList();
	}
	
	public Drug save(DrugDto drugDto) throws Exception {
		
		Drug drug = new Drug(drugDto.getName(),drugDto.getForm(), drugDto.isReceipt(), drugDto.getCode(), drugDto.getDailyDose(), drugDto.getWeight(), drugDto.getType(),
				drugDto.getManufacturer(), drugDto.getSubstitutions(), drugDto.getIngredients(), drugDto.getDescription(),
				drugDto.getPoint(),0);
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

	@Transactional
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

	@Transactional
	public Drug update(DrugDto drugDto) throws Exception {
		Drug drug = new Drug(drugDto.getName(),drugDto.getForm(), drugDto.isReceipt(), drugDto.getCode(), drugDto.getDailyDose(), drugDto.getWeight(), drugDto.getType(),
				drugDto.getManufacturer(), drugDto.getSubstitutions(), drugDto.getIngredients(), drugDto.getDescription(),
				drugDto.getPoint(),0);
		drug.setId(drugDto.getId());
		if (drugRepository.findOneById(drug.getId()) == null) {
			throw new Exception("Drug doesn't exists");
		}
		if (drug.getIngredients().isEmpty()) {
			throw new Exception("Empty ingredients list!");
		}

		if (!areIngredientsValid(drug.getIngredients())) {
			throw new Exception("This ingrediant does not exist");
		}

		return drugRepository.save(drug);
	}

	public List<Drug> findAllSubstitutes(String drugId) {
		Drug drug = drugRepository.findById(drugId).orElse(null);
		return drug.getSubstitutions();
		
	}
	
	public List<Drug> findAllSubstitutesDrugstore(String drugId, String drugstoreId, String patientId) {
		Drug drug = drugRepository.findById(drugId).orElse(null);
		List<Drug> drugList =  drug.getSubstitutions();
		
		

		Drugstore drugstore = drugstoreRepository.findById(drugstoreId).orElse(null);
		List<DrugStock> drugStockList = drugStockRepository.findByDrugstore(drugstore);

		ArrayList<Drug> wanted = new ArrayList<Drug>();
		ArrayList<Drug> wantedAllergens = new ArrayList<Drug>();

		for(DrugStock ds : drugStockList) {
			
			for(Drug d : drugList) {
				if(ds.getDrug().getId().equals(d.getId()) && ds.getAmount()>0) {
					wanted.add(d);
				}
			}
		}
		//provera za alergicnost
		Patient pat = patientRepository.findById(patientId).orElse(null);
		for(Drug d : wanted) {
			int foundAllergen = 0;
			for(Ingredient ing : pat.getAllergens()) {
				if(d.getIngredients().contains(ing)) {
					foundAllergen = 1;
					break;
				}
			}
			if(foundAllergen == 0) {
				wantedAllergens.add(d);
			}
		}
		
		return wantedAllergens;
	}

	public List<Drug> getDrugsNotOnStock(String drugstoreId) {
		List<DrugStock> drugsOnStock = drugStockRepository.findByDrugstore(drugstoreRepository.findById(drugstoreId).orElse(null), null);
		List<Drug> drugs = drugRepository.findAll();
		for (DrugStock ds : drugsOnStock) {
			drugs.remove(ds.getDrug());
		}
		return drugs;
	}

	public List<CreateDrugOrderDto> getDrugsForCreateOrderView(String drugstoreId, String drugsToBeShown) {
		List<Drug> drugsNotOnStock = getDrugsNotOnStock(drugstoreId);
		List<DrugStock> drugsOnStock = drugStockRepository.findByDrugstore(drugstoreRepository.findById(drugstoreId).orElse(null), null);
		List<CreateDrugOrderDto> returnValue = new ArrayList<CreateDrugOrderDto>();
		Drugstore drugstore = drugstoreRepository.findById(drugstoreId).orElse(null);
		
		
		for (Drug drug : drugsNotOnStock) {
			List<DrugRequest> dr = drugRequestRepository.findByDrugAndDrugstore(drug, drugstore);
			if (drugsToBeShown.equals("All_Drugs") || !dr.isEmpty()) {
				List<DrugOrder> orders = drugOrderRepository.findByDrugstore(drugstore);
				boolean flag = false;
				for (DrugOrder order : orders) {
					if (order.getStatus() == OrderStatus.Active || order.getStatus() == OrderStatus.Pending) {
						for (OrderStock stock : order.getStock()) {
							if (stock.getDrug() == drug) {
								flag = true;
								break;
							}
						}
						if (flag) 
							break;
					}
				}
				returnValue.add(new CreateDrugOrderDto(drug.getName(), false, 0, !dr.isEmpty(), flag));
			}
		}
		
		
		for (DrugStock ds : drugsOnStock) {
			List<DrugRequest> dr = drugRequestRepository.findByDrugAndDrugstore(ds.getDrug(), drugstore);
			if (drugsToBeShown.equals("All_Drugs") || !dr.isEmpty()) {
					List<DrugOrder> orders = drugOrderRepository.findByDrugstore(drugstore);
					boolean flag = false;
					for (DrugOrder order : orders) {
						if (order.getStatus() == OrderStatus.Active || order.getStatus() == OrderStatus.Pending) {
							for (OrderStock stock : order.getStock()) {
								if (stock.getDrug() == ds.getDrug()) {
									flag = true;
									break;
								}
							}
							if (flag) 
								break;
						}
					}
				returnValue.add(new CreateDrugOrderDto(ds.getDrug().getName(), true, ds.getAmount(), !dr.isEmpty(), flag));
			}
		}
		return returnValue;
	}

	public Integer returnDrugsLength(DrugSearchDto searchDto) {
		return drugRepository.findAll(DrugSpecifications.withSearch(searchDto)).size();
	}
	
	public Drug getOne(String id) {
		return drugRepository.findById(id).orElse(null);
	}
	public Drug getOneName(String name) {
		return drugRepository.findByName(name);
	}
}
