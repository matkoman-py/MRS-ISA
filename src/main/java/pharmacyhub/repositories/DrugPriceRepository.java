package pharmacyhub.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import pharmacyhub.domain.Drug;
import pharmacyhub.domain.DrugPrice;
import pharmacyhub.domain.Drugstore;

public interface DrugPriceRepository extends JpaRepository<DrugPrice, String> {
	List<DrugPrice> findByDrugAndDrugstore(Drug drug, Drugstore drugstore);

	List<DrugPrice> findByDrug(Drug drug);
	
	@Transactional
	long deleteByDrug(Drug drug);

	void deleteByDrugstore(Drugstore drugstoreToDelete);
}
