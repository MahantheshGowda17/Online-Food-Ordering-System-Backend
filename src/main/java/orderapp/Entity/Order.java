package orderapp.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import jakarta.persistence.Table;
import orderapp.Enum.OrderStatus;

@Data
@Entity
@Table(name="Orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@JsonIgnore
	@ManyToOne
	private Restaurant restaurant;
	
	@OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
	private List<OrderItem> orderItems;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	private Double totalPrice;
	
	@JsonIgnore
	@ManyToOne
	private User user;
	

}
