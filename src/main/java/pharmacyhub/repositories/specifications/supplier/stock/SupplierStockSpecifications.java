package pharmacyhub.repositories.specifications.supplier.stock;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import pharmacyhub.domain.SupplierStock;
import pharmacyhub.dto.search.SupplierStockSearchDto;

public class SupplierStockSpecifications {
	public static Specification<SupplierStock> withSearch(SupplierStockSearchDto supplierStockSearchDto){
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			
			if(supplierStockSearchDto.getDrugName() != null && !supplierStockSearchDto.getDrugName().isBlank()) {
				predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + supplierStockSearchDto.getDrugName().toLowerCase() + "%"));
			}
			
			if(supplierStockSearchDto.getSupplierId() != null && !supplierStockSearchDto.getSupplierId().isBlank()) {
				predicates.add(criteriaBuilder.equal(root.get("supplier").get("id"), supplierStockSearchDto.getSupplierId()));
			}
			
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			
		};
	}
}
