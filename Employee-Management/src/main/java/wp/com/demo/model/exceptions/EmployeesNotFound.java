package wp.com.demo.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeesNotFound extends RuntimeException {
    public EmployeesNotFound(Long CompanyId){
        super(String.format("No employees were found in the company with id: %s",CompanyId));
    }
}

