package pharmacyhub.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.DrugPrice;
import pharmacyhub.domain.DrugStock;
import pharmacyhub.dto.DrugStockPriceDto;
import pharmacyhub.repositories.DrugPriceRepository;
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
	
	public List<DrugStock> findAll() {
		return drugStockRepository.findAll();
	}
	
	public List<DrugStockPriceDto> returnDrugStockForDrugstore(String drugstoreId) {
		List<DrugStock> drugStocks = drugStockRepository.findByDrugstore(drugstoreRepository.findById(drugstoreId).orElse(null), null);
		System.out.println(drugStocks.size());
		List<DrugStockPriceDto> drugsStockPriceDto = new ArrayList<DrugStockPriceDto>();
		for (DrugStock ds : drugStocks) {
			List<DrugPrice> dpList = drugPriceRepository.findByDrugAndDrugstore(ds.getDrug(), ds.getDrugstore());
			DrugPrice dp = getLastPrice(dpList);
			drugsStockPriceDto.add(new DrugStockPriceDto(ds.getDrug().getId(), ds.getDrug().getName(), ds.getAmount(), dp.getPrice(), dp.getEndDate()));
		}
		return drugsStockPriceDto;
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
	
}
