package orderapp.Service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

import orderapp.Entity.Food;
import orderapp.Entity.Order;
import orderapp.Entity.Restaurant;

public interface RestaurantService {
	
	Restaurant createRestaurant(Restaurant restaurant);
	
	Restaurant getById(Integer id);
	
//  List<Restaurant> findAll();
	
	Page getAllResturants(int pageNum, int pageSize, String sortBy);
	
	Restaurant updateRestaurant(Integer id, Restaurant updateRestaurant);
	
	void deleteRestaruaant(Integer id);
	
	Restaurant assignFood(Integer restaurantId,Set<Integer> foodId);
	
	List<Food> findFoodByRestaurantId(Integer id);
	
	List<Order> findOrdersByRestaurantId(Integer id);
}
