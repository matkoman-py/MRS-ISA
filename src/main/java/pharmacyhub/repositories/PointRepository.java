package pharmacyhub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pharmacyhub.domain.Point;

public interface PointRepository extends JpaRepository<Point, String>  {

}
