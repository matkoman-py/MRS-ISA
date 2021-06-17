package pharmacyhub.repositories;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.transaction.annotation.Transactional;

import pharmacyhub.domain.DrugOrder;
import pharmacyhub.domain.Offer;
import pharmacyhub.domain.enums.OfferStatus;

public interface OfferRepository extends JpaRepository<Offer, String>, JpaSpecificationExecutor<Offer>{
	
//	@Transactional
//	@Lock(LockModeType.OPTIMISTIC)
	List<Offer> findByDrugOrderOrderByIdAsc(DrugOrder drugOrder);
	
	@Transactional
	@Lock(LockModeType.OPTIMISTIC)
	Offer findByIdAndStatus(String id, OfferStatus status);
	
//	@Transactional
//	@Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
//	Optional<Offer> findById(String id);


}
