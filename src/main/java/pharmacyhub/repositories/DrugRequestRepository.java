package pharmacyhub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import pharmacyhub.domain.Drug;
import pharmacyhub.domain.DrugRequest;
import pharmacyhub.domain.Drugstore;


public interface DrugRequestRepository extends JpaRepository<DrugRequest, String>{
	
	List<DrugRequest> findByDrugAndDrugstore(Drug drug, Drugstore drugstore);
	@Transactional
	void deleteByDrugstoreAndDrug(Drugstore drugstore, Drug drug);
	
}
