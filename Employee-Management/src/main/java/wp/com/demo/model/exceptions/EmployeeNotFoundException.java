package wp.com.demo.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import wp.com.demo.model.Employee;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException  extends RuntimeException{
    public EmployeeNotFoundException(Long id) {
        super(String.format("Employee with id: %s was not found", id));
    }
}




