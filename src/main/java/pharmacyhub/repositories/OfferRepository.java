package pharmacyhub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import pharmacyhub.domain.Offer;

public interface OfferRepository extends JpaRepository<Offer, String>, JpaSpecificationExecutor<Offer>{

}
