package orderapp.Service.Impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import orderapp.Entity.Food;
import orderapp.Entity.Restaurant;
import orderapp.Repository.FoodRepository;
import orderapp.Repository.RestaurantRepository;
import orderapp.Service.FoodService;

@Service
public class FoodServiceImplementation  implements FoodService{

	@Autowired
	private FoodRepository foodRepository;
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Override
	public Food createFood(Food food) {
		return foodRepository.save(food);
	}


	@Override
	public Food getFoodById(Integer id) {
		Optional<Food> response = foodRepository.findById(id);
		if(response.isPresent()) {
			return response.get();
		} else {
			throw new NoSuchElementException("Food with ID " + id + " not found");
		}
	}


	@Override
	public Page<Food> getAllFood(int pageNum, int pageSize) {
		String sortBy = "id";
		Sort sort = Sort.by(sortBy).descending();
		PageRequest pageable = PageRequest.of(pageNum, pageSize,sort);
		Page page = foodRepository.findAll(pageable);
		return page;
	}


	@Override
	public Food updateFood(Food food, Integer id) {
		Food existing=getFoodById(id);
		existing.setName(food.getName());
		existing.setDescription(food.getDescription());
		existing.setPrice(food.getPrice());
		return foodRepository.save(existing);
	}


	@Override
	public void deleteFood(Integer id) {
		Food food = getFoodById(id);
		List<Restaurant> restaurants =food.getRestaurants();
		if(restaurants.size()==0) {
			foodRepository.delete(food);
			return;
		}
		for(Restaurant restaurant:restaurants) {
			restaurant.getFood().remove(food);
		}
		restaurantRepository.saveAll(restaurants);
		foodRepository.delete(food);
		
	}



}
