package pharmacyhub.repositories;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.transaction.annotation.Transactional;

import pharmacyhub.domain.DrugOrder;
import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.enums.OrderStatus;

public interface DrugOrderRepository extends JpaRepository<DrugOrder, String>, JpaSpecificationExecutor<DrugOrder>{
	
	@Transactional
	@Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
	DrugOrder findByIdAndStatus(String id, OrderStatus status);
	List<DrugOrder> findByDrugstore(Drugstore drugstore);
	
}
