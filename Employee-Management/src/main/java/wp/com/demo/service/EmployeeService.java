package wp.com.demo.service;

import org.springframework.web.multipart.MultipartFile;
import wp.com.demo.model.Company;
import wp.com.demo.model.Employee;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {

Optional<Employee> findById(Long id);
   Employee findById(Employee id);

List<Employee>findByCompanyName(String name);
Optional <Employee>save(Company companyId, String name, String surname, MultipartFile profilePicture, String imageSource, String embg, String email, String street, String city, String country, String jobTitle, String department, LocalDate employmentDate, String status,
                       String phone, Integer projects, Integer salary, Integer experience);
    Optional<Employee>edit(Long id,Company companyId,String name, String surname,MultipartFile profilePicture,String imageSource,String embg,String email, String street,String city,String country,String jobTitle,String department,LocalDate employmentDate,String status,
                           String phone,Integer projects, Integer salary,Integer experience);
   // List<Employee>findAllByCompanyName(String company_name);

    List<Employee>listAll();
    void  deleteById(Long id);
    Employee findCompanyId (Company companyId);
    public Employee addEmployeeToCompany(Company company, Employee employee);
    public List<Employee>listByCompanyId(Company company);
    public void deleteByCompanyId(Company id);
    public void deleteEmployeesByCompany(List<Employee> employees);








}
