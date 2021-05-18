package pharmacyhub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import pharmacyhub.domain.DrugOrder;
import pharmacyhub.domain.Offer;

public interface OfferRepository extends JpaRepository<Offer, String>, JpaSpecificationExecutor<Offer>{
	
	List<Offer> findByDrugOrder(DrugOrder drugOrder);

}
