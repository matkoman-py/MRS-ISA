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
import pharmacyhub.domain.users.DrugstoreAdmin;
import pharmacyhub.dto.search.DrugstoreSearchDto;
import pharmacyhub.dto.search.EReceiptSearchDto;
import pharmacyhub.repositories.DrugPriceRepository;
import pharmacyhub.repositories.DrugRepository;
import pharmacyhub.repositories.DrugRequestRepository;
import pharmacyhub.repositories.DrugStockRepository;
import pharmacyhub.repositories.DrugstoreRepository;
import pharmacyhub.repositories.LocationRepository;
import pharmacyhub.repositories.specifications.drugstores.DrugstoreSpecifications;
import pharmacyhub.repositories.users.DrugstoreAdminRepository;
import pharmacyhub.repositories.users.PharmacistRepository;
import pharmacyhub.repositories.users.UserRepository;

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
	
	@Autowired
	private UserRepository userRepository;
	
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
	
	public List<Drugstore> eReceiptSearch(EReceiptSearchDto eReceiptSearchDto, Pageable pageable) {
		
		return drugstoreRepository.findAll(DrugstoreSpecifications.eReceiptSearch(eReceiptSearchDto), pageable).toList();
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

	public Drugstore getAdminsDrugstore(String adminId) {
		DrugstoreAdmin admin = (DrugstoreAdmin) userRepository.findById(adminId).orElse(null);
		return admin.getDrugstore();
	}

	public boolean drugstoreUpdate(Drugstore drugstore) throws Exception {
		Drugstore d = drugstoreRepository.findById(drugstore.getId()).orElse(null);
		if (d == null) {
			throw new Exception("This drugstore does not exist!");
		}
		if(d.getName() != null) d.setName(drugstore.getName());
		if(d.getDescription() != null) d.setDescription(drugstore.getDescription());
		if(d.getWorkingHoursFrom() != null) d.setWorkingHoursFrom(drugstore.getWorkingHoursFrom());
		if(d.getWorkingHoursTo() != null) d.setWorkingHoursTo(drugstore.getWorkingHoursTo());
		if(d.getPharmacistAppointmentPrice() != 0) d.setPharmacistAppointmentPrice(drugstore.getPharmacistAppointmentPrice());
		
		Location location = d.getLocation();
		
		if(d.getLocation().getAddress() != null) location.setAddress(drugstore.getLocation().getAddress());
		if(d.getLocation().getCity() != null) location.setCity(drugstore.getLocation().getCity());
		if(d.getLocation().getCountry() != null) location.setCountry(drugstore.getLocation().getCountry());

		d.setLocation(location);
		
		drugstoreRepository.save(d);
		return true;
	}
}

