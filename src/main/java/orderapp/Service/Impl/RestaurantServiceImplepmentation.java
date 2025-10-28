package orderapp.Service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import orderapp.Entity.Food;
import orderapp.Entity.Order;
import orderapp.Entity.Restaurant;
import orderapp.Repository.FoodRepository;
import orderapp.Repository.RestaurantRepository;
import orderapp.Service.RestaurantService;

@Service
public class RestaurantServiceImplepmentation implements RestaurantService {
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private FoodRepository foodRepository;


	@Override
	public Restaurant createRestaurant(Restaurant restaurant) {
		return  restaurantRepository.save(restaurant);

	}

	@Override
	public Restaurant getById(Integer id) {
		  Optional<Restaurant> response = restaurantRepository.findById(id);
		  if(response.isPresent()) { 
			  return response.get(); 
			  } else { 
				  throw new NoSuchElementException("Restaurant with ID:" +id+ " not found");
				  }
		//return restaurantRepo.findById(id).orElseThrow({}->new NoSuchElementException("Restaurant with ID:" +id+ " not found"));
	}

	/*
	 * @Override public List<Restaurant> findAll() { return
	 * restaurantRepo.findAll(); }
	 */

	@Override
	public Page getAllResturants(int pageNum, int pageSize, String sortBy) {
		Sort sort = Sort.by(sortBy).descending();
		PageRequest pageable = PageRequest.of(pageNum, pageSize,sort);
		Page page = restaurantRepository.findAll(pageable);
		return page;
	}

	@Override
	public Restaurant updateRestaurant(Integer id, Restaurant updateRestaurant) {
		Restaurant existing=getById(id);
		existing.setAddress(updateRestaurant.getAddress());
		existing.setContactNumber(updateRestaurant.getContactNumber());
		existing.setEmail(updateRestaurant.getEmail());
		existing.setName(updateRestaurant.getName());	
		return restaurantRepository.save(existing);
	}

	@Override
	public void deleteRestaruaant(Integer id) {
	    Restaurant restaurant = getById(id); // throws if not found
	    restaurantRepository.delete(restaurant);
	}

	
	@Override
	public Restaurant assignFood(Integer restaurantId, Set<Integer> foodId) {
		Restaurant restaurant = getById(restaurantId);
		
		java.util.List<Food> foodItems = new ArrayList();
		
		for(Integer id : foodId) {
			Food food = foodRepository.findById(id).orElseThrow(()->new NoSuchElementException("Food with ID: "+id+" not found"));
			foodItems.add(food);
		}
		
		restaurant.setFood(foodItems);
		
		return restaurantRepository.save(restaurant);
	}

	@Override
	public List<Food> findFoodByRestaurantId(Integer id) {
		List<Food> food = restaurantRepository.findFoodByRestaurantId(id);
		if(food==null || food.size()==0) {
			throw new NoSuchElementException("Restaurant With ID:" + id + " not found or the food is not" + "assigned to restaurant");
		}
		else {
			return food;
		}
	}

	@Override
	public List<Order> findOrdersByRestaurantId(Integer id) {
		List<Order> orders = restaurantRepository.findOrderByRestaurantid(id);
		if(orders==null || orders.size()==0) {
			throw new NoSuchElementException("Restaurant with id" +id+ "Not fount or there are orders");
		}else {
		return orders;
		}
	}
}
