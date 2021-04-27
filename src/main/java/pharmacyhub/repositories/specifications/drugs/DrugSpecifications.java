package pharmacyhub.repositories.specifications.drugs;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import pharmacyhub.domain.Drug;
import pharmacyhub.dto.search.DrugSearchDto;

public class DrugSpecifications {
	public static Specification<Drug> withSearch(DrugSearchDto drugSearchDto) {
		return (drug, query, criteriaBuilder) -> {

			List<Predicate> predicates = new ArrayList<>();
			
			if(drugSearchDto.getName() != null && !drugSearchDto.getName().isBlank()) {
				predicates.add(criteriaBuilder.like(criteriaBuilder.lower(drug.get("name")), "%" + drugSearchDto.getName().toLowerCase() + "%"));
			}
			
			if(drugSearchDto.getForm() != null && !drugSearchDto.getForm().isBlank()) {
				predicates.add(criteriaBuilder.like(criteriaBuilder.lower(drug.get("form")), "%" + drugSearchDto.getForm().toLowerCase() + "%"));
			}
			
			if(drugSearchDto.getType() != null && !drugSearchDto.getType().isBlank()) {
				predicates.add(criteriaBuilder.like(criteriaBuilder.lower(drug.get("type").get("name")), "%" + drugSearchDto.getType().toLowerCase() + "%"));
			}
			
			if(drugSearchDto.getManufacturerId() != null && !drugSearchDto.getManufacturerId().isBlank()) {
				predicates.add(criteriaBuilder.equal(drug.get("manufacturer").get("id"), drugSearchDto.getManufacturerId()));
			}
			
			if(drugSearchDto.isReceipt() != null) {
				predicates.add(criteriaBuilder.equal(drug.get("receipt"), drugSearchDto.isReceipt()));
			}
			
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
		
	}
}
