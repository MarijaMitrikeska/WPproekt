package wp.com.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import wp.com.demo.model.User;
import wp.com.demo.model.enums.Role;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    User register(String username,String email, String password, String repeatPassword, Role role);

    Optional<User> findByUsername(String username);

    User getUsername(String username);


}



