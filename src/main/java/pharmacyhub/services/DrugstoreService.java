package pharmacyhub.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.DrugRequest;
import pharmacyhub.domain.DrugStock;
import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.Location;
import pharmacyhub.dto.search.DrugstoreSearchDto;
import pharmacyhub.repositories.DrugPriceRepository;
import pharmacyhub.repositories.DrugRepository;
import pharmacyhub.repositories.DrugRequestRepository;
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
	private DrugStockRepository drugStockRepository;
	
	@Autowired
	private DrugPriceRepository drugPriceRepository;
	
	@Autowired
	private PharmacistRepository pharmacistRepository;
	
	@Autowired
	private DrugstoreAdminRepository drugstoreAdminRepository;
	
	@Autowired
	private DrugRequestRepository drugRequestRepository;
	
	@Autowired
	private DrugRepository drugRepository;
	
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
	
	public Drugstore findDrugstore(String drugstoreId) throws Exception {
		
		return drugstoreRepository.findById(drugstoreId).orElse(null);
	}

	public List<DrugStock> findDrugstores(String id, Pageable pageable) {
		List<DrugStock> drugsOnStock = drugStockRepository.findByDrugId(id, pageable);
		return drugsOnStock;
	}

	public List<DrugStock> findDrugstoreEmployee(String drugId, String drugstoreId) {
		List<DrugStock> drugsOnStock = drugStockRepository.findByDrugId(drugId/*, pageable*/);
		List<DrugStock> wanted = new ArrayList<DrugStock>();
		for(DrugStock ds : drugsOnStock) {
			if(ds.getDrugstore().getId().equals(drugstoreId)) {
				wanted.add(ds);
			}
		}
		if(wanted.isEmpty()) {
			//napravi drug request
			DrugRequest dr = new DrugRequest(drugstoreRepository.findById(drugstoreId).orElse(null),drugRepository.findById(drugId).orElse(null),false);
			drugRequestRepository.save(dr);
		}
		return wanted;
	}
}

