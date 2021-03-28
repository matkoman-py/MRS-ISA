package pharmacyhub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pharmacyhub.domain.Drugstore;

public interface DrugstoreRepository extends JpaRepository<Drugstore, String>{

}
