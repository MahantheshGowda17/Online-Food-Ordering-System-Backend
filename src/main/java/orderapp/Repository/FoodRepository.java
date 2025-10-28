package orderapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import orderapp.Entity.Food;

public interface FoodRepository extends JpaRepository<Food, Integer> {

}
