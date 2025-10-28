package orderapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import orderapp.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
