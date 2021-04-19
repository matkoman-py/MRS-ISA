package pharmacyhub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.DrugPrice;
import pharmacyhub.dto.CreateNewPriceForDrugDto;
import pharmacyhub.repositories.DrugPriceRepository;
import pharmacyhub.repositories.DrugRepository;
import pharmacyhub.repositories.DrugstoreRepository;

@Service
public class DrugPriceService {
	
	@Autowired 
	private DrugPriceRepository drugPriceRepository;
	
	@Autowired 
	private DrugRepository drugRepository;
	
	@Autowired 
	private DrugstoreRepository drugstoreRepository;
	
	public List<DrugPrice> findAll() {
		return drugPriceRepository.findAll();
	}
	
	public DrugPrice save(CreateNewPriceForDrugDto drugPrice) throws Exception {
		return drugPriceRepository.save(new DrugPrice(drugRepository.findByName(drugPrice.getDrugName()), drugstoreRepository.findById(drugPrice.getDrugStoreId()).orElse(null), drugPrice.getPrice(), drugPrice.getStartDate(), drugPrice.getEndDate(), false));
	}

	public DrugPrice savePromotion(CreateNewPriceForDrugDto drugPromotion) throws Exception {
		return drugPriceRepository.save(new DrugPrice(drugRepository.findByName(drugPromotion.getDrugName()), drugstoreRepository.findById(drugPromotion.getDrugStoreId()).orElse(null), drugPromotion.getPrice(), drugPromotion.getStartDate(), drugPromotion.getEndDate(), true));
	}
	
}
