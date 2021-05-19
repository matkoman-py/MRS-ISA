package pharmacyhub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pharmacyhub.domain.PatientCategory;

public interface PatientCategoryRepository extends JpaRepository<PatientCategory, String>{
	List<PatientCategory> findByRequieredPointsGreaterThanOrderByRequieredPointsAsc(int points);
}
