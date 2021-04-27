package pharmacyhub.repositories.specifications.drugOrders;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import pharmacyhub.domain.DrugOrder;
import pharmacyhub.dto.search.DrugOrderSearchDto;

public class DrugOrderSpecifications {
	public static Specification<DrugOrder> withSearch(DrugOrderSearchDto drugOrderSearchDto) {
		return (drugOrder, query, criteriaBuilder) -> {

			List<Predicate> predicates = new ArrayList<>();
			
			if(drugOrderSearchDto.getOrderStatus() != null) {
				predicates.add(criteriaBuilder.equal(drugOrder.get("drugstore").get("id"), drugOrderSearchDto.getDrugstoreId()));
			}
			
			if(drugOrderSearchDto.getOrderStatus() != null) {
				predicates.add(criteriaBuilder.equal(drugOrder.get("status"), drugOrderSearchDto.getOrderStatus()));
			}
			
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
