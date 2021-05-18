package pharmacyhub.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.Drug;
import pharmacyhub.domain.DrugStock;
import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.Location;
import pharmacyhub.dto.DrugStockPriceDto;
import pharmacyhub.dto.ereceipt.DrugstoreAndPriceDto;
import pharmacyhub.dto.ereceipt.ReceiptSearchResultsDto;
import pharmacyhub.dto.search.DrugstoreSearchDto;
import pharmacyhub.dto.search.EReceiptSearchDto;
import pharmacyhub.repositories.DrugPriceRepository;
import pharmacyhub.repositories.DrugRepository;
import pharmacyhub.repositories.DrugStockRepository;
import pharmacyhub.repositories.DrugstoreRepository;
import pharmacyhub.repositories.LocationRepository;
import pharmacyhub.repositories.specifications.drugstores.DrugstoreSpecifications;
import pharmacyhub.repositories.users.DrugstoreAdminRepository;
import pharmacyhub.repositories.users.PharmacistRepository;

@Service
public class DrugstoreService {
	
	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private DrugstoreRepository drugstoreRepository;
	
	@Autowired
	private DrugRepository drugRepository;
	
	@Autowired
	private DrugStockRepository drugStockRepository;
	
	@Autowired
	private DrugPriceRepository drugPriceRepository;
	
	@Autowired
	private PharmacistRepository pharmacistRepository;
	
	@Autowired
	private DrugstoreAdminRepository drugstoreAdminRepository;
	
	@Autowired
	private DrugStockService drugstockService;
	
	
	public List<Drugstore> findAll() {
		return drugstoreRepository.findAll();
	}
	
	public Drugstore update(Drugstore drugstore) throws Exception {
		
		//TODO: odraditi provere za vreme
		
		if(drugstoreRepository.findById(drugstore.getId()) == null) {
			throw new Exception("No such drugstore");
		}
		
		return save(drugstore);
	}
	

	public Drugstore save(Drugstore drugstore) throws Exception {

		if(drugstore.getLocation() != null) {
			Location location = drugstore.getLocation();
			locationRepository.save(location);
		}
		
		return drugstoreRepository.save(drugstore);
	}
	
	public void delete(String id) throws Exception {
		Drugstore drugstoreToDelete = drugstoreRepository.findById(id).orElse(null);
		if(drugstoreToDelete == null) {
			throw new Exception("No such drugstore");
		}
		
		drugStockRepository.deleteByDrugstore(drugstoreToDelete);
		drugPriceRepository.deleteByDrugstore(drugstoreToDelete);
		drugstoreAdminRepository.deleteByDrugstore(drugstoreToDelete);
		pharmacistRepository.deleteByDrugstore(drugstoreToDelete);
		
		drugstoreRepository.delete(drugstoreToDelete);
	}

	public List<Drugstore> returnDrugStores(DrugstoreSearchDto drugstoreSearchDto, Pageable pageable) {
		
		return drugstoreRepository.findAll(DrugstoreSpecifications.withSearch(drugstoreSearchDto), pageable).toList();
	}
	
	public ReceiptSearchResultsDto eReceiptSearch(EReceiptSearchDto eReceiptSearchDto, Pageable pageable) {
		ReceiptSearchResultsDto receiptSearchResultsDto = new ReceiptSearchResultsDto();
		receiptSearchResultsDto.setDrugs(getReceiptDrugs(eReceiptSearchDto));
		receiptSearchResultsDto.setDrugstores(getReceiptDrugstoresAndPrices(eReceiptSearchDto, pageable));
		return receiptSearchResultsDto;
	}
	
	private double getSumOfDrugPrices(Drugstore drugstore, EReceiptSearchDto eReceiptSearchDto) {
		double sum = 0;
		List<DrugStockPriceDto> drugStockPrices = drugstockService.returnDrugStockForDrugstore(drugstore.getId());
		
		for(String drugId : eReceiptSearchDto.getReceiptData().keySet()) {
			for (DrugStockPriceDto drugStockPrice : drugStockPrices) {
				if (drugId.equals(drugStockPrice.getDrugId())) {
					sum += drugStockPrice.getDrugPrice();
				}
			}
		}
		return sum;
	}
	
	private List<DrugstoreAndPriceDto> getReceiptDrugstoresAndPrices(EReceiptSearchDto eReceiptSearchDto, Pageable pageable) {
		List<Drugstore> drugstores = drugstoreRepository.findAll(DrugstoreSpecifications.eReceiptSearch(eReceiptSearchDto), pageable).toList();
		List<DrugstoreAndPriceDto> drugstoresWithPrices = new ArrayList<>();
		
		boolean checkFromPrice = false;
		boolean checkToPrice = false;
		
		if(eReceiptSearchDto.getFromPrice() != null && eReceiptSearchDto.getFromPrice() > 0 && eReceiptSearchDto.getFromPrice() < eReceiptSearchDto.getToPrice()) {
			checkFromPrice = true;
		}
		
		if(eReceiptSearchDto.getToPrice() != null && eReceiptSearchDto.getToPrice() > 0 && eReceiptSearchDto.getToPrice() > eReceiptSearchDto.getFromPrice()) {
			checkToPrice = true;
		}
		
		for (Drugstore drugstore : drugstores) {
			DrugstoreAndPriceDto drugstoreAndPriceDto = new DrugstoreAndPriceDto(drugstore, getSumOfDrugPrices(drugstore, eReceiptSearchDto));
			if (checkFromPrice && drugstoreAndPriceDto.getTotalPrice() < eReceiptSearchDto.getFromPrice()) {
				continue;
			}
			if (checkToPrice && drugstoreAndPriceDto.getTotalPrice() > eReceiptSearchDto.getToPrice()) {
				continue;
			}
			drugstoresWithPrices.add(drugstoreAndPriceDto);

		}
		return drugstoresWithPrices;
	}
	
	
	private List<Drug> getReceiptDrugs(EReceiptSearchDto eReceiptSearchDto) {
		List<Drug> drugs = new ArrayList<>();
		for(String drugId : eReceiptSearchDto.getReceiptData().keySet()) {
			drugs.add(drugRepository.findById(drugId).orElse(null));
		}
		return drugs;
	}
	
	public Drugstore findDrugstore(String drugstoreId) throws Exception {
		
		return drugstoreRepository.findById(drugstoreId).orElse(null);
	}

	public List<DrugStock> findDrugstores(String id/*, Pageable pageable*/) {
		List<DrugStock> drugsOnStock = drugStockRepository.findByDrugId(id/*, pageable*/);
		return drugsOnStock;
	}
}

