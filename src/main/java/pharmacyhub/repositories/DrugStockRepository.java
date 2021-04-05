package pharmacyhub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pharmacyhub.domain.DrugStock;
import pharmacyhub.domain.Drugstore;

public interface DrugStockRepository extends JpaRepository<DrugStock, String>{
	List<DrugStock> findByDrugstore(Drugstore drugstore);

}
