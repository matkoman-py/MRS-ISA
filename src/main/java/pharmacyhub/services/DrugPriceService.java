package pharmacyhub.services;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.Drug;
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
		if (drugPrice.getPrice() <= 0) {
			throw new Exception("Price must be greater than 0!");
		}
		return drugPriceRepository.save(new DrugPrice(drugRepository.findByName(drugPrice.getDrugName()), drugstoreRepository.findById(drugPrice.getDrugStoreId()).orElse(null), drugPrice.getPrice(), drugPrice.getStartDate(), drugPrice.getEndDate()));
	}
	
}
