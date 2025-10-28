package orderapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import orderapp.Dto.BillResponse;
import orderapp.Dto.OrderRequest;
import orderapp.Dto.PaymentDto;
import orderapp.Dto.ResponseStructure;
import orderapp.Entity.Order;
import orderapp.Enum.OrderStatus;
import orderapp.Service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping("/generate-bill")
	public ResponseEntity<ResponseStructure<BillResponse>> generateBill (@RequestBody OrderRequest orderRequest){
		BillResponse response = orderService.generateBill(orderRequest);
		ResponseStructure<BillResponse> apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("Bill Has Been Generated");
		apiResponse.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
	}
	
	@PostMapping("/pay-and-place-order")
	public ResponseEntity<ResponseStructure<String>> payAndPlaceOrder(@RequestBody PaymentDto payment){
		String data = orderService.payAndPlaceOrder(payment);
		ResponseStructure<String> apiResponse = new ResponseStructure();
		apiResponse.setData(data);
		apiResponse.setMessage("Order placed");
		apiResponse.setStatusCode(HttpStatus.CREATED.value());
		
		return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
	}
	@DeleteMapping("/{id}/delete")
	public ResponseEntity deleteOrder(@PathVariable Integer id) {
		orderService.deleteOrder(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@PatchMapping("/{id}/updateStatus")
	public ResponseEntity<ResponseStructure<Order>> updateStatus(@PathVariable Integer id, @RequestParam OrderStatus status)
	{
		Order order= orderService.updateStatusByAdmin(status, id);
		ResponseStructure<Order> apiResponse= new ResponseStructure<>();
		apiResponse.setData(order);
		apiResponse.setMessage("Status Updated");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
		
	}
	
	@PatchMapping("/{id}/cancelOrder")
	public ResponseEntity<ResponseStructure<String>> cancelOrder(@PathVariable Integer id){
		String response = orderService.cancleOrder(id);
		ResponseStructure<String> apiResponse= new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("Order cancelled");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		
		return ResponseEntity.ok(apiResponse);
	}
}
