package pharmacyhub.repositories;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.transaction.annotation.Transactional;

import pharmacyhub.domain.Drug;
import pharmacyhub.domain.SupplierStock;
import pharmacyhub.domain.users.Supplier;

public interface SupplierStockRepository extends JpaRepository<SupplierStock, String>, JpaSpecificationExecutor<SupplierStock>{
	
	SupplierStock findByDrugAndSupplier(Drug drug, Supplier supplier);
	
	@Transactional
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	SupplierStock findByDrugIdAndSupplierId(String drugId, String supplierId);
}
