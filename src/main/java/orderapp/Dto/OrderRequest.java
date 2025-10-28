package orderapp.Dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderRequest {
	@NotNull
	private List<OrderItemRequest> orderitems;
	@NotNull(message="restaurant ID cannot be null")
	private Integer restaurantId;
}
