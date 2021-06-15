package pharmacyhub.repositories;

import java.util.List;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import pharmacyhub.domain.Drug;
import pharmacyhub.domain.DrugStock;
import pharmacyhub.domain.Drugstore;

public interface DrugStockRepository extends JpaRepository<DrugStock, String>{
	List<DrugStock> findByDrugstore(Drugstore drugstore, Pageable pageable);
	List<DrugStock> findByDrugId(String drugId, Pageable pageable);
	List<DrugStock> findByDrugId(String drugId);
	List<DrugStock> findByDrugstore(Drugstore drugstore);


	@Transactional
	@Lock(LockModeType.PESSIMISTIC_WRITE)
//	@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
	DrugStock findByDrugAndDrugstore(Drug drug, Drugstore drugstore);

	@Transactional
	void deleteByDrug(Drug drug);

	@Transactional
	List<DrugStock> deleteByDrugstore(Drugstore drugstoreToDelete);

	@Transactional
	void deleteByDrugAndDrugstore(Drug drug, Drugstore drugstore);
}
