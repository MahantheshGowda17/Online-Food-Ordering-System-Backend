package orderapp.Service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import orderapp.Dto.BillResponse;
import orderapp.Dto.OrderItemRequest;
import orderapp.Dto.OrderRequest;
import orderapp.Dto.PaymentDto;
import orderapp.Entity.Food;
import orderapp.Entity.Order;
import orderapp.Entity.OrderItem;
import orderapp.Entity.Restaurant;
import orderapp.Entity.User;
import orderapp.Enum.OrderStatus;
import orderapp.Exception.PaymentFailedException;
import orderapp.Repository.OrderRepository;
import orderapp.Service.FoodService;
import orderapp.Service.OrderService;
import orderapp.Service.RestaurantService;
import orderapp.Service.UserService;

@Service
@RequiredArgsConstructor
public class OrderServiceImplementation implements OrderService {

	
	private final RestaurantService restaurantService;
	private final FoodService foodService;
	private final OrderRepository orderRepository;
	private final UserService userService;


	@Override
	public BillResponse generateBill(OrderRequest orderRequest) {
		Restaurant restaurant =restaurantService.getById(orderRequest.getRestaurantId());
		StringBuilder summary = new StringBuilder();
		float totalPrice=0;
		
		for(OrderItemRequest orderItems : orderRequest.getOrderitems()) {
			Food food = foodService.getFoodById(orderItems.getFoodId());
			float price = food.getPrice() * orderItems.getQuantity();
			totalPrice+=price;
			summary.append(food.getName()).append(" X ").append(orderItems.getQuantity()).append(" = ").append(price).append("\n");
			
		}
		return new BillResponse(restaurant.getName(),summary.toString(), totalPrice);
	}

	@Override
	public String payAndPlaceOrder(PaymentDto payment) {
		//simulate payment
		if(payment.isPaymentSuccessful()) {
			Order order = new Order();
			order.setStatus(OrderStatus.PLACED);
			
			Restaurant restaurant = restaurantService.getById(payment.getRestaurantId());
			//set restaurant to order
			order.setRestaurant(restaurant);
			
			//set user to order
			User user = userService.getUser(payment.getUserId());
			order.setUser(user);
			
			List<OrderItem> items = new ArrayList();
			double totalPrice=0;
			
			for(OrderItemRequest request : payment.getOrderItems()) {
				Food food = foodService.getFoodById(request.getFoodId());
				
				OrderItem orderItem = new OrderItem();
				orderItem.setFood(food);
				orderItem.setQuantity(request.getQuantity());
				orderItem.setOrder(order);
				
				items.add(orderItem);
				
				double price = food.getPrice() * request.getQuantity();
				totalPrice += price;
			}
			
			order.setTotalPrice(totalPrice);
			order.setOrderItems(items);
			orderRepository.save(order);
			return "Order has been placed";
		}
		else {
			throw new PaymentFailedException("Payment was not successful, hence order cannot be placed");
		}
	}

	@Override
	public void deleteOrder(Integer id) {
		Order order = getOrder(id);
		orderRepository.delete(order);
	}

	@Override
	public Order getOrder(Integer id) {
		Optional<Order> order = orderRepository.findById(id);
		if(order.isPresent()) {
			return order.get();
		}
		throw new NoSuchElementException("order with id" + id + " Does Not exist ");
		
	}

	@Override
	public Order updateStatusByAdmin(OrderStatus status, Integer id) {
		Order order =getOrder(id);
		order.setStatus(status);
		return orderRepository.save(order);
	}

	@Override
	public String cancleOrder(Integer id) {
		Order order=getOrder(id);
		order.setStatus(OrderStatus.CANCELLED);
		orderRepository.save(order);
		return "Order Cancelled, your money will be refunded in 2 business hours"; 
	}


	

}
