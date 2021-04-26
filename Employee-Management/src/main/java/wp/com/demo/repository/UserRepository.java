package wp.com.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wp.com.demo.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    Optional<User>findByUsernameAndPassword(String username,String password);

    Optional<User>findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
