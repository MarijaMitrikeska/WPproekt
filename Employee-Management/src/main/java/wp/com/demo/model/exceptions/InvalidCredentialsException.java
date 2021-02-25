package wp.com.demo.model.exceptions;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(){
        super("Invalid user credentials");
    }
}
