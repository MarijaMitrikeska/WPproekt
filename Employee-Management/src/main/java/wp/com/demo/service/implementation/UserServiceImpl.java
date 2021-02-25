package wp.com.demo.service.implementation;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import wp.com.demo.model.User;
import wp.com.demo.model.enums.Role;
import wp.com.demo.model.exceptions.EmailAlreadyAssociatedException;
import wp.com.demo.model.exceptions.InvalidCredentialsException;
import wp.com.demo.model.exceptions.UsernameExistsException;
import wp.com.demo.repository.UserRepository;
import wp.com.demo.service.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String email,String password, String repeatPassword,Role role) {
        if(username==null || username.isEmpty()|| password==null|| password.isEmpty() || email==null || email.isEmpty())
            throw new InvalidCredentialsException();
        if (!password.equals(repeatPassword))
            throw new InvalidCredentialsException(); // nov exception treba
        emailCheck(email);
        if (this.userRepository.findByUsername(username).isPresent())
            throw new UsernameExistsException(username);
        User user=new User(username,email,passwordEncoder.encode(password),role);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {

        return this.userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(()->new UsernameNotFoundException(s));
    }

    public void emailCheck(String email) {
        if (this.userRepository.existsByEmail(email)) throw new EmailAlreadyAssociatedException(email);
    }

    public void usernameCheck(String username) {
        if(this.userRepository.existsByUsername(username)) throw new UsernameExistsException(username);
    }
}
