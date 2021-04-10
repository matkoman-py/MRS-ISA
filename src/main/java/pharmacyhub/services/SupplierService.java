package pharmacyhub.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.users.Supplier;
import pharmacyhub.repositories.LocationRepository;
import pharmacyhub.repositories.users.SupplierRepository;

@Service
public class SupplierService {
	
	@Autowired
	private SupplierRepository supplierRepository;
	
	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	private UserNotificationService userNotificationService;
	
	public Supplier save(Supplier supplier) throws Exception {

		locationRepository.save(supplier.getLocation());

		supplierRepository.save(supplier);
		userNotificationService.sendEmployeeInitialPassword(supplier.getEmail(), supplier.getPassword());

		return supplierRepository.save(supplier);
	}


}
