package pharmacyhub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import pharmacyhub.domain.Drug;
import pharmacyhub.domain.SupplierStock;
import pharmacyhub.domain.users.Supplier;

public interface SupplierStockRepository extends JpaRepository<SupplierStock, String>, JpaSpecificationExecutor<SupplierStock>{
	SupplierStock findByDrugAndSupplier(Drug drug, Supplier supplier);
}
