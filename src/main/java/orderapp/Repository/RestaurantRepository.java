package orderapp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import orderapp.Entity.Food;
import orderapp.Entity.Order;
import orderapp.Entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>{
	
	@Query("SELECT r.food FROM Restaurant r WHERE r.id =:restaurantId")
	List<Food> findFoodByRestaurantId(@Param(value ="restaurantId") int id);

	@Query("SELECT r.orders FROM Restaurant r WHERE r.id=:restaurantId")
	List<Order> findOrderByRestaurantid(@Param(value="restaurantId")int id);
}
