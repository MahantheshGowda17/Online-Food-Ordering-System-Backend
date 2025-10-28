package orderapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
import orderapp.Service.FoodService;

@RestController
@RequestMapping("/food/api")
public class FoodController {
	
	@Autowired
	private FoodService foodService;
	
	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<Food>> createFood(@RequestBody Food food){
		Food saved = foodService.createFood(food);
		ResponseStructure<Food> apiResponse = new ResponseStructure<>();
		apiResponse.setData(saved);
		apiResponse.setMessage("Food added Successfully!");
		apiResponse.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<ResponseStructure<Food>> getFoodById(@PathVariable Integer id){
		Food food = foodService.getFoodById(id);
		ResponseStructure<Food> apiResponse = new ResponseStructure<>();
		apiResponse.setData(food);
		apiResponse.setMessage("Food Items Found Successfuly!");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<ResponseStructure<Page>> getAllFood(
			@RequestParam(defaultValue ="0", required = false) int pageNum,
			@RequestParam(defaultValue ="5", required = false)int pageSize) {
		Page response = foodService.getAllFood(pageNum,pageSize);
		ResponseStructure<Page> apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("Api ran Successfully!");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseStructure<Food>> updateFood(
			@PathVariable Integer id,
			@RequestBody Food updateFood) {
		Food updated = foodService.updateFood(updateFood, id);
		ResponseStructure<Food>response = new ResponseStructure<>();
		response.setData(updated);
		response.setMessage("Resturant Update Successfully1");
		response.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
			
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteFood(@PathVariable Integer id) {
	   foodService.deleteFood(id);
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}		
	
	
	
}
