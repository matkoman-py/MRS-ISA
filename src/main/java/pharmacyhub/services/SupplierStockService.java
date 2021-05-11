package pharmacyhub.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.Drug;
import pharmacyhub.domain.SupplierStock;
import pharmacyhub.domain.users.Supplier;
import pharmacyhub.dto.search.SupplierStockSearchDto;
import pharmacyhub.dto.supplier.EditSupplierStockDto;
import pharmacyhub.dto.supplier.SupplierStockDto;
import pharmacyhub.repositories.DrugRepository;
import pharmacyhub.repositories.SupplierStockRepository;
import pharmacyhub.repositories.specifications.supplier.stock.SupplierStockSpecifications;
import pharmacyhub.repositories.users.SupplierRepository;

@Service
public class SupplierStockService {

	@Autowired
	private SupplierStockRepository supplierStockRepository;
	
	@Autowired
	private DrugRepository drugRepository;
	
	@Autowired
	private SupplierRepository supplierRepository;
	
	private SupplierStockDto getSupplierStockDto(SupplierStock supplierStock) {
		
		SupplierStockDto supplierStockDto = new SupplierStockDto();
		supplierStockDto.setDrugId(supplierStock.getDrug().getId());
		supplierStockDto.setDrugName(supplierStock.getDrug().getName());
		supplierStockDto.setSupplierId(supplierStock.getSupplier().getId());
		supplierStockDto.setId(supplierStock.getId());
		supplierStockDto.setAmount(supplierStock.getAmount());
		
		return supplierStockDto;
	}
	
	public List<SupplierStockDto> search(SupplierStockSearchDto searchCriteria, Pageable pageable){
		List<SupplierStock> result = supplierStockRepository.findAll(SupplierStockSpecifications.withSearch(searchCriteria), pageable).toList();
		return result
				.stream()
				.map(supplierStock -> getSupplierStockDto(supplierStock)).collect(Collectors.toList());
	}
	
	
	public SupplierStockDto add(SupplierStockDto supplierStockToAdd) throws Exception {
		
		Drug drug = drugRepository.findById(supplierStockToAdd.getDrugId()).orElse(null);
		if (drug == null) {
			throw new Exception("No such drug");
		}
		
		Supplier supplier = supplierRepository.findById(supplierStockToAdd.getSupplierId()).orElse(null);
		if (supplier == null) {
			throw new Exception("No such supplier");
		}
		
		SupplierStock existingSupplierStock = supplierStockRepository.findByDrugAndSupplier(drug, supplier);
		
		if(existingSupplierStock != null) {
			existingSupplierStock.setAmount(existingSupplierStock.getAmount() + supplierStockToAdd.getAmount());
			supplierStockRepository.save(existingSupplierStock);
			return getSupplierStockDto(existingSupplierStock);
		}
		
		if(supplierStockToAdd.getAmount() < 0) {
			throw new Exception("Supplier stock amount can not be negative");
		}
		
		SupplierStock newStock = supplierStockRepository.save(new SupplierStock(drug, supplier, supplierStockToAdd.getAmount()));
		
		return getSupplierStockDto(newStock);
	}
	
	public Boolean delete(String id) throws Exception {
		
		SupplierStock supplierStock = supplierStockRepository.findById(id).orElse(null);
		
		if(supplierStock == null) {
			throw new Exception("No such supplier stock");
		}
		
		supplierStockRepository.delete(supplierStock);
		return true;
		
	}
	
	public SupplierStockDto update(EditSupplierStockDto editedSupplierStock) throws Exception {
		
		SupplierStock supplierStock = supplierStockRepository.findById(editedSupplierStock.getId()).orElse(null);
		
		if(editedSupplierStock.getAmount() < 0) {
			throw new Exception("Supplier stock amount can not be negative");
		}
		
		if(supplierStock == null) {
			throw new Exception("No such supplier stock");
		}
		
		supplierStock.setAmount(editedSupplierStock.getAmount());
		
		SupplierStock editedStock = supplierStockRepository.save(supplierStock);
		return getSupplierStockDto(editedStock);
	}
	
}
