package pharmacyhub.repositories.specifications.offers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import pharmacyhub.domain.Offer;
import pharmacyhub.dto.search.OfferSearchDto;

public class OfferSpecifications {
	public static Specification<Offer> withSearch(OfferSearchDto offerSearchDto){
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			
			if(offerSearchDto.getOfferStatus() != null) {
				predicates.add(criteriaBuilder.equal(root.get("status"), offerSearchDto.getOfferStatus()));
			}
			
			if(offerSearchDto.getSupplierId() != null && !offerSearchDto.getSupplierId().isBlank()) {
				predicates.add(criteriaBuilder.equal(root.get("supplier").get("id"), offerSearchDto.getSupplierId()));
			}
			
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			
		};
	}
}
