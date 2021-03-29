package pharmacyhub.services;

import java.util.ArrayList;
>>>>>>> 7869d7d881929db2ebae84a109f4aabebf3ef784
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.Drugstore;
import pharmacyhub.repositories.DrugstoreRepository;

@Service
public class DrugstoreService {

	@Autowired
	private DrugstoreRepository drugstoreRepository;
	
	public List<Drugstore> findAll() {
		return drugstoreRepository.findAll();
	}

	public Drugstore save(Drugstore drugstore) throws Exception {

		//treba proveriti da li je validno
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

