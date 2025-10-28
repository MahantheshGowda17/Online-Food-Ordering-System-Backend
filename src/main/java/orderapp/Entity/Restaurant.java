package orderapp.Entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name; 
	private String address;
	private Long contactNumber;
	private String email;
	@CreationTimestamp
	private LocalDate createdAt;
	@UpdateTimestamp
	private LocalDate updateAt;
	      
	@ManyToMany
	@JoinTable(name="restaurant_food",joinColumns = @JoinColumn(name="id_restaurant"),inverseJoinColumns = @JoinColumn(name="id_food"))
	private List<Food> food;
	
	
	@OneToMany(mappedBy = "restaurant")
	private List<Order> orders;
	

}
