package pharmacyhub.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.Location;
import pharmacyhub.repositories.DrugstoreRepository;
import pharmacyhub.repositories.LocationRepository;

@Service
public class DrugstoreService {
	
	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private DrugstoreRepository drugstoreRepository;
	
	public List<Drugstore> findAll() {
		return drugstoreRepository.findAll();
	}

	public Drugstore save(Drugstore drugstore) throws Exception {

		if(drugstore.getLocation() != null) {
			Location location = drugstore.getLocation();
			locationRepository.save(location);
		}
		
		return drugstoreRepository.save(drugstore);
	}

	public List<Drugstore> returnDrugStores(String drugStoreName,String drugStoreCity,String drugStoreCountry) {
		
		List<Drugstore> allDrugStores = findAll();
		List<Drugstore> wantedDrugStores = new ArrayList<>();
		
		for(Drugstore drs:allDrugStores) {
			if( (drs.getName().equals(drugStoreName) || drugStoreName.equals("0")) && 
				(drs.getLocation().getCity().equals(drugStoreCity) || drugStoreCity.equals("0")) && 
				(drs.getLocation().getCountry().equals(drugStoreCountry) || drugStoreCountry.equals("0"))) {
				wantedDrugStores.add(drs);
			}
		}
		return wantedDrugStores;
	}
}

