package orderapp.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import orderapp.Dto.ResponseStructure;
import orderapp.Entity.User;
import orderapp.Service.UserService;

@Tag(name = "User Controller", description = "Operations related to user management")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	@Operation(summary = "Creates an user")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "User created successfully"),
			@ApiResponse(responseCode = "500", description = "Internal server error"),
			@ApiResponse(responseCode = "400", description = "Method arguments invalid")
	})

	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<User>> createUser(@Valid @RequestBody User user) {
		User response = userService.createUser(user);

		ResponseStructure<User> apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("User created");
		apiResponse.setStatusCode(HttpStatus.CREATED.value()); // This is fine for your custom structure

		return new ResponseEntity<>(apiResponse, HttpStatus.CREATED); // Pass enum, not int
	}

	
	@Operation(summary = "Fetchs an user")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "user fetched"),
			@ApiResponse(responseCode = "404", description = "user with ID does not exist"),
			@ApiResponse(responseCode = "400", description = "bad request")
	})
	
	@GetMapping("/{id}/get")
	public ResponseEntity<ResponseStructure<User>> getUser(@PathVariable Integer id) {
		User response = userService.getUser(id);
		ResponseStructure<User> apiRespose = new ResponseStructure();
		apiRespose.setData(response);
		apiRespose.setMessage("User is found");
		apiRespose.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<User>>(apiRespose, HttpStatus.OK);
	}

	@Operation(summary = "Fetches all user")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "fetches all users"),
			@ApiResponse(responseCode = "500", description = "internal server error")
	})
	
	
	@GetMapping("/getAll")
	public ResponseEntity<ResponseStructure<List<User>>> getAllUser() {
		List<User> response = userService.getAllUsers();
		ResponseStructure<List<User>> apiRespose = new ResponseStructure();
		apiRespose.setData(response);
		apiRespose.setMessage("Users found");
		apiRespose.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(apiRespose, HttpStatus.OK);
	}

	@Operation(summary = "Updates an user")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "user updated successfully"),
			@ApiResponse(responseCode = "404", description = "user with ID not found"),
			@ApiResponse(responseCode = "400", description = "method argument invalid"),
	})
	
	
	@PutMapping("/{id}/update")
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user, @PathVariable Integer id) {
		User response = userService.updateUser(user, id);
		ResponseStructure<User> apiRespose = new ResponseStructure();
		apiRespose.setData(response);
		apiRespose.setMessage("User updated");
		apiRespose.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(apiRespose, HttpStatus.OK);
	}

	
	@Operation(summary = "Deletes an user")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "user deleted successfully"),
			@ApiResponse(responseCode = "404", description = "user with ID not found")
	})
	
	
	@DeleteMapping("/{id}/delete")
	public ResponseEntity deleteUser(@PathVariable Integer id) {
		userService.deleteUser(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	
	@Operation(summary = "Image uploaded successfully")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Image uploaded successfully"),
			@ApiResponse(responseCode = "404", description = "User with ID not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	
	
	@PatchMapping("/{id}/user/uploading")
	public ResponseEntity<ResponseStructure<String>> uploadImage(@RequestParam MultipartFile image,
			@PathVariable Integer id) throws IOException {
		String response = userService.uploadImage(image, id);
		ResponseStructure<String> apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("Success");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	
	@Operation(summary = "Fetches image")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Image fetched successfully"),
			@ApiResponse(responseCode = "404", description = "User with ID not found")
	})
	
	
	@GetMapping(value = "/{id}/user/getImage")
	public ResponseEntity<byte[]> getImge(@PathVariable Integer id) {
		byte[] image = userService.getImage(id);
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);

	}

}
