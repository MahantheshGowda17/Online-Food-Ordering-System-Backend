package orderapp.Service;

import orderapp.Dto.BillResponse;
import orderapp.Dto.OrderRequest;
import orderapp.Dto.PaymentDto;
import orderapp.Entity.Order;
import orderapp.Enum.OrderStatus;

public interface OrderService {

	BillResponse generateBill(OrderRequest orderRequest) ;
	
	String payAndPlaceOrder(PaymentDto payment);
	
	void deleteOrder(Integer id);
	
	Order getOrder(Integer id);
	
	Order updateStatusByAdmin(OrderStatus status,Integer id);
	
	String cancleOrder(Integer id);
}
