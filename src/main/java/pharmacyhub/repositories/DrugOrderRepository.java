package pharmacyhub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import pharmacyhub.domain.DrugOrder;

public interface DrugOrderRepository extends JpaRepository<DrugOrder, String>, JpaSpecificationExecutor<DrugOrder>{

}
