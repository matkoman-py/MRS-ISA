package pharmacyhub.repositories.specifications.drugstores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import pharmacyhub.domain.DrugStock;
import pharmacyhub.domain.Drugstore;
import pharmacyhub.dto.search.DrugstoreSearchDto;
import pharmacyhub.dto.search.EReceiptSearchDto;

public class DrugstoreSpecifications {
	public static Specification<Drugstore> withSearch(DrugstoreSearchDto drugstoreSearchDto){
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			
			if(drugstoreSearchDto.getName() != null && !drugstoreSearchDto.getName().isBlank()) {
				predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + drugstoreSearchDto.getName().toLowerCase() + "%"));
			}
			
			if(drugstoreSearchDto.getLocation().getCity() != null && !drugstoreSearchDto.getLocation().getCity().isBlank()) {
				predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("location").get("city")), "%" + drugstoreSearchDto.getLocation().getCity().toLowerCase() + "%"));
			}
			
			if(drugstoreSearchDto.getLocation().getCountry() != null && !drugstoreSearchDto.getLocation().getCountry().isBlank()) {
				predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("location").get("country")), "%" + drugstoreSearchDto.getLocation().getCountry().toLowerCase() + "%"));
			}
			
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			
		};
	}
	
	public static Specification<Drugstore> eReceiptSearch(EReceiptSearchDto eReceiptSearchDto){
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			
			
//			drugStocksJoin.on(criteriaBuilder.equal(drugStocksJoin.get("drugstore").get("id"), root.get("id")));
			
			for (String drugId : eReceiptSearchDto.getReceiptData().keySet()) {
				Join<Drugstore, DrugStock> drugStocksJoin = root.join("drugStock");

				Predicate equalPredicate = criteriaBuilder.equal(drugStocksJoin.get("drug").get("id"), drugId);
				predicates.add(equalPredicate);
			}
						
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			
		};
	}
}
