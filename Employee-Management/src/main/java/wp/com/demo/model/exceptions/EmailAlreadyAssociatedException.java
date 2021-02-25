package wp.com.demo.model.exceptions;

import java.util.function.Supplier;

public class EmailAlreadyAssociatedException extends RuntimeException  {

    public EmailAlreadyAssociatedException(String email) {
        super(String.format("The email %s is already associated with an account.", email));
    }
}
