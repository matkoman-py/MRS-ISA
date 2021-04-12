package pharmacyhub.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.Location;
import pharmacyhub.repositories.DrugPriceRepository;
import pharmacyhub.repositories.DrugStockRepository;
import pharmacyhub.repositories.DrugstoreRepository;
import pharmacyhub.repositories.LocationRepository;
import pharmacyhub.repositories.users.DrugstoreAdminRepository;
import pharmacyhub.repositories.users.PharmacistRepository;

@Service
public class DrugstoreService {
	
	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private DrugstoreRepository drugstoreRepository;
	
	@Autowired
	private DrugStockRepository drugStockRepository;
	
	@Autowired
	private DrugPriceRepository drugPriceRepository;
	
	@Autowired
	private PharmacistRepository pharmacistRepository;
	
	@Autowired
	private DrugstoreAdminRepository drugstoreAdminRepository;
	
	
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

	public List<Drugstore> returnDrugStores(String drugStoreName,String drugStoreCity,String drugStoreCountry) {
		
		List<Drugstore> allDrugStores = findAll();
		List<Drugstore> wantedDrugStores = new ArrayList<>();
		
		for(Drugstore drs:allDrugStores) {
			if( (drs.getName().toLowerCase().contains(drugStoreName.toLowerCase()) || drugStoreName.equals("0")) && 
				(drs.getLocation().getCity().toLowerCase().contains(drugStoreCity.toLowerCase()) || drugStoreCity.equals("0")) && 
				(drs.getLocation().getCountry().toLowerCase().contains(drugStoreCountry.toLowerCase()) || drugStoreCountry.equals("0"))) {
				wantedDrugStores.add(drs);
			}
		}
		return wantedDrugStores;
	}
	
	public Drugstore findDrugstore(String drugstoreId) throws Exception {
		
		return drugstoreRepository.findById(drugstoreId).orElse(null);
	}
}

