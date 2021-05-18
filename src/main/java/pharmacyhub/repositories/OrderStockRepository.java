package pharmacyhub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pharmacyhub.domain.OrderStock;

public interface OrderStockRepository extends JpaRepository<OrderStock, String>{

}
