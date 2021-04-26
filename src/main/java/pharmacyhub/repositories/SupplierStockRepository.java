package pharmacyhub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.SupplierStock;

public interface SupplierStockRepository extends JpaRepository<SupplierStock, String>, JpaSpecificationExecutor<SupplierStock>{

}
