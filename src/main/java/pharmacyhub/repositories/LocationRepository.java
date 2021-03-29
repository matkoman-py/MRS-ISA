package pharmacyhub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pharmacyhub.domain.Location;

public interface LocationRepository extends JpaRepository<Location, String> {

}
