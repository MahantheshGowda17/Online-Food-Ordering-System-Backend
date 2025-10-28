package orderapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import orderapp.Entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{

}
