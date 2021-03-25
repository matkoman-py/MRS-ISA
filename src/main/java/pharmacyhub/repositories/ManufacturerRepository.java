package pharmacyhub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pharmacyhub.domain.Manufacturer;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, String>{
	
}
