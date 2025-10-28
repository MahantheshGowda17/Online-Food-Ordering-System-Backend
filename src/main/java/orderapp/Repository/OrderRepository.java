package orderapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import orderapp.Entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
