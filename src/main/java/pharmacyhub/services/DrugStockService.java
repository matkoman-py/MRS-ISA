package pharmacyhub.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.Drug;
import pharmacyhub.domain.DrugPrice;
import pharmacyhub.domain.DrugReservation;
import pharmacyhub.domain.DrugStock;
import pharmacyhub.domain.Drugstore;
import pharmacyhub.dto.AddNewDrugToDrugstoreDto;
import pharmacyhub.dto.DeleteDrugFromStockDto;
import pharmacyhub.dto.DrugStockPriceDto;
import pharmacyhub.repositories.DrugPriceRepository;
import pharmacyhub.repositories.DrugRepository;
import pharmacyhub.repositories.DrugReservationRepository;
import pharmacyhub.repositories.DrugStockRepository;
import pharmacyhub.repositories.DrugstoreRepository;

@Service
public class DrugStockService {
	
	@Autowired 
	private DrugStockRepository drugStockRepository;
	
	@Autowired
	private DrugstoreRepository drugstoreRepository;
	
	@Autowired
	private DrugPriceRepository drugPriceRepository;
	
	@Autowired
	private DrugRepository drugRepository;
	
	@Autowired
	private DrugReservationRepository drugReservationRepository;
	
	public List<DrugStock> findAll() {
		return drugStockRepository.findAll();
	}
	
	public List<DrugStockPriceDto> returnDrugStockForDrugstore(String drugstoreId) {
		List<DrugStock> drugStocks = drugStockRepository.findByDrugstore(drugstoreRepository.findById(drugstoreId).orElse(null), null);
		List<DrugStockPriceDto> drugsStockPriceDto = new ArrayList<DrugStockPriceDto>();
		for (DrugStock ds : drugStocks) {
			List<DrugPrice> dpList = drugPriceRepository.findByDrugAndDrugstore(ds.getDrug(), ds.getDrugstore());
			DrugPrice dp = getLastPrice(dpList);
			drugsStockPriceDto.add(new DrugStockPriceDto(ds.getDrug().getId(), ds.getDrug().getName(), ds.getAmount(), dp.getPrice(), dp.getEndDate()));
		}
		return drugsStockPriceDto;
	}
	
	public DrugPrice getLastPriceByDrugAndDrugStore(Drug drug, Drugstore drugstore) {
		List<DrugPrice> drugPrices = drugPriceRepository.findByDrugAndDrugstore(drug, drugstore);
		return getLastPrice(drugPrices);
	}
	
	public DrugPrice getLastPrice(List<DrugPrice> dpList) {
		int currentPrice = Integer.MAX_VALUE;
		DrugPrice promotion = null;
		Date today = new Date();
		for (DrugPrice dp : dpList) {
			if (dp.isPromotion() && dp.getEndDate().after(today)) {
				if (currentPrice > dp.getPrice()) {
					currentPrice = dp.getPrice();
					promotion = dp;
				}
			}
		}
		if (promotion != null)
			return promotion;
		System.out.println(dpList.size());
		return Collections.max(dpList, Comparator.comparing(ds -> ds.getEndDate()));
	}
	
	public List<DrugStockPriceDto> search(String searchedText, String drugstoreId) {
		List<DrugStockPriceDto> allDrugsOnStock = returnDrugStockForDrugstore(drugstoreId);
		List<DrugStockPriceDto> searchedList = new ArrayList<DrugStockPriceDto>();
		for (DrugStockPriceDto ds : allDrugsOnStock) {
			if (ds.getDrugName().toLowerCase().contains(searchedText.toLowerCase())) {
				searchedList.add(ds);
			}
		}
		return searchedList;
	}

	public Boolean addNewDrugToDrugstore(AddNewDrugToDrugstoreDto drugDto) {
		DrugStock ds = new DrugStock(drugDto.getSelectedDrug(), drugstoreRepository.findById(drugDto.getDrugStoreId()).orElse(null), 0);
		drugStockRepository.save(ds);
		DrugPrice dp = new DrugPrice(drugDto.getSelectedDrug(), drugstoreRepository.findById(drugDto.getDrugStoreId()).orElse(null), drugDto.getPrice(), drugDto.getStartDate(), drugDto.getEndDate(), false);
		drugPriceRepository.save(dp);
		return true;
	}

	public String deleteFromStock(DeleteDrugFromStockDto deleteDrugDto) {
		Drug drug = drugRepository.findByName(deleteDrugDto.getDrugName());
		Drugstore drugstore = drugstoreRepository.findById(deleteDrugDto.getDrugStoreId()).orElse(null);
		List<DrugReservation> drugReservations = drugReservationRepository.findByDrugAndDrugstore(drug, drugstore);
		if (drugReservations.isEmpty()) {
			drugStockRepository.deleteByDrugAndDrugstore(drug, drugstore);
			return "Drug deleted from stock successfully!";
		}
		return "Drug not deleted from stock beacuse there are active reservations for this drug!";
	}
	
}
