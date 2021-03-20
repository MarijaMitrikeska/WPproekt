package wp.com.demo.service;

import org.springframework.web.multipart.MultipartFile;
import wp.com.demo.model.Company;
import wp.com.demo.model.Employee;
import wp.com.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
//    Company addCompany(String name, String description,String moto, String owner, Integer employee, Integer interns);
    Company update(User user,String name, String description, String moto, String owner, MultipartFile profilePicture, String imageSource, Integer employee, Integer interns);
    void delete(String name);
    void deleteById(Long id);
    List<Company>listEmployees();
    List<Company>searchEmployees(String search);
    List<Company>listCompanies();

    Optional<Company>edit(User user,Long id, String name, String desc,String owner, String moto, MultipartFile profilePicture,String imageSource, Integer numEm, Integer numInt);

    Optional<Company>save(User user, String name, String desc, String owner, String moto, MultipartFile profilePicture, String imageSource, Integer numEm, Integer numInt);
    Optional<Company>findById(Long id);

    Company addEmployeeToCompany(User username, Long employeeId);
    Optional<Company> getCompany(User username);
    List<Employee>listEmployeesInCompany(Long companyId);


}
