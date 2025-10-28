package orderapp.Controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import orderapp.Dto.ResponseStructure;
import orderapp.Entity.Food;
import orderapp.Entity.Order;
import orderapp.Entity.Restaurant;
import orderapp.Service.RestaurantService;

@RestController
@RequestMapping("/restaurant/api")
public class RestaurantController {
	
	@Autowired
	private RestaurantService restaurantService;
	
	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<Restaurant>> createRestaurant(@RequestBody Restaurant restaurant){
		Restaurant response = restaurantService.createRestaurant(restaurant);
		ResponseStructure apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("Restaurant Created Successfully!");
		apiResponse.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<ResponseStructure<Restaurant>> getById(@PathVariable Integer id) {
		Restaurant response = restaurantService.getById(id);
		ResponseStructure apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("Restaurant Found Successfully!");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
	}
	
	/*
	 * @GetMapping("/getAll") public
	 * ResponseEntity<ResponseStructure<List<Restaurant>>> findAll() {
	 * List<Restaurant> response = restaurantService.findAll();
	 * ResponseStructure<List<Restaurant>> apiResponse = new ResponseStructure<>();
	 * apiResponse.setData(response);
	 * apiResponse.setMessage("All Restaurants Found Successfully!");
	 * apiResponse.setStatusCode(HttpStatus.OK.value()); return new
	 * ResponseEntity<>(apiResponse, HttpStatus.OK); }
	 */
	
	@GetMapping("/getAll")
	public ResponseEntity<ResponseStructure<Page>> getAllResturants(
			@RequestParam(defaultValue ="0", required = false) int pageNum,
			@RequestParam(defaultValue ="5", required = false)int pageSize,
			@RequestParam(defaultValue ="createdAt", required=false)String sortBy) {
		Page response = restaurantService.getAllResturants(pageNum,pageSize,sortBy);
		ResponseStructure<Page> apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("Api ran Successfully!");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseStructure<Restaurant>> updateRestaurnat(
			@PathVariable Integer id,
			@RequestBody Restaurant updateRestaurnat) {
	
		Restaurant updated = restaurantService.updateRestaurant(id, updateRestaurnat);
		ResponseStructure<Restaurant> response = new ResponseStructure<>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Restaurant updated successfully");
		response.setData(updated);
		return new ResponseEntity<>(response,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteRestaurant(@PathVariable Integer id) {
	    restaurantService.deleteRestaruaant(id);
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	
	@PostMapping("/{restaurantId}/assignFood")
	public ResponseEntity<ResponseStructure<Restaurant>> assignFood(@PathVariable Integer restaurantId, @RequestBody Set<Integer> food){
		Restaurant restaurant = restaurantService.assignFood(restaurantId, food);
		ResponseStructure<Restaurant> apiResponse = new ResponseStructure<>();
		apiResponse.setData(restaurant);
		apiResponse.setMessage("Assigned");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("/{id}/getAll")
	public ResponseEntity<ResponseStructure<List<Food>>> getFoodByRestaurant(@PathVariable Integer id){
		ResponseStructure<List<Food>> apiResponse = new ResponseStructure<>();
		apiResponse.setData(restaurantService.findFoodByRestaurantId(id));
		apiResponse.setMessage("Food Items found");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	

	@GetMapping("/{id}/getAllOrders")
	public ResponseEntity<ResponseStructure<List<Order>>> getOrdersByRestaurant(@PathVariable Integer id){
		ResponseStructure<List<Order>> apiResponse = new ResponseStructure<>();
		apiResponse.setData(restaurantService.findOrdersByRestaurantId(id));
		apiResponse.setMessage("Orders Items found");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
}
