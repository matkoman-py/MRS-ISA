package pharmacyhub.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import pharmacyhub.domain.Drug;
import pharmacyhub.domain.DrugStock;
import pharmacyhub.domain.Drugstore;
import pharmacyhub.domain.users.Dermatologist;

public interface DrugStockRepository extends JpaRepository<DrugStock, String>{
	List<DrugStock> findByDrugstore(Drugstore drugstore, Pageable pageable);
	List<DrugStock> findByDrugId(String drugId, Pageable pageable);
	List<DrugStock> findByDrugId(String drugId);
	DrugStock findByDrugAndDrugstore(Drug drug, Drugstore drugstore);

	@Transactional
	void deleteByDrug(Drug drug);

	List<DrugStock> deleteByDrugstore(Drugstore drugstoreToDelete);

	@Transactional
	void deleteByDrugAndDrugstore(Drug drug, Drugstore drugstore);
}
