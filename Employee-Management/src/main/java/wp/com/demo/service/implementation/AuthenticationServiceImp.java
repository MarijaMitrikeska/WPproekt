package wp.com.demo.service.implementation;

import org.springframework.stereotype.Service;
import wp.com.demo.model.User;
import wp.com.demo.model.exceptions.InvalidCredentialsException;
import wp.com.demo.repository.UserRepository;
import wp.com.demo.service.AuthenticationService;

@Service
public class AuthenticationServiceImp implements AuthenticationService {
   private final UserRepository userRepository;

    public AuthenticationServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidCredentialsException();
        }
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidCredentialsException::new);
    }

}

