package wp.com.demo.service;

import wp.com.demo.model.User;

public interface AuthenticationService {
    User login(String username, String password);
}
