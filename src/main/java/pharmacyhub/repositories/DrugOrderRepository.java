package pharmacyhub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import pharmacyhub.domain.Drug;
import pharmacyhub.domain.DrugOrder;
import pharmacyhub.domain.Drugstore;

public interface DrugOrderRepository extends JpaRepository<DrugOrder, String>, JpaSpecificationExecutor<DrugOrder>{
	
	List<DrugOrder> findByDrugstore(Drugstore drugstore);
	
}
