package pharmacyhub.repositories.specifications.drugstores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import pharmacyhub.domain.Drugstore;
import pharmacyhub.dto.search.DrugstoreSearchDto;

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
}
