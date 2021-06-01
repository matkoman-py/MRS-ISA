package pharmacyhub.repositories.specifications;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import pharmacyhub.domain.Drug;
import pharmacyhub.domain.users.Patient;
import pharmacyhub.dto.search.DrugSearchDto;

public class PatientSpecifications {
	public static Specification<Patient> withSearch(String patientName, String patientSurname) {
		return (patient, query, criteriaBuilder) -> {

			List<Predicate> predicates = new ArrayList<>();
			
			if(patientName != null && !patientName.isBlank()) {
				predicates.add(criteriaBuilder.like(criteriaBuilder.lower(patient.get("name")), "%" + patientName.toLowerCase() + "%"));
			}
			
			if(patientSurname != null && !patientSurname.isBlank()) {
				predicates.add(criteriaBuilder.like(criteriaBuilder.lower(patient.get("surname")), "%" + patientSurname.toLowerCase() + "%"));
			}
			
			
			
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
		
	}
}
