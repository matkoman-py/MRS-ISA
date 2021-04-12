package pharmacyhub.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import pharmacyhub.domain.Drug;
import pharmacyhub.domain.DrugStock;
import pharmacyhub.domain.Drugstore;

public interface DrugStockRepository extends JpaRepository<DrugStock, String>{
	List<DrugStock> findByDrugstore(Drugstore drugstore);

	@Transactional
	void deleteByDrug(Drug drug);

	List<DrugStock> deleteByDrugstore(Drugstore drugstoreToDelete);

}
