package wp.com.demo.service;

import org.springframework.web.multipart.MultipartFile;
import wp.com.demo.model.Employee;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {

Optional<Employee> findById(Long id);

List<Employee>findByCompanyName(String name);
Optional<Employee>save(String name, String surname, MultipartFile profilePicture, String imageSource, String embg, String email, String street, String city, String country, String jobTitle, String department, LocalDate employmentDate, String status,
                       String phone, Integer projects, Integer salary, Integer experience);
    Optional<Employee>edit(Long id,String name, String surname,MultipartFile profilePicture,String imageSource,String embg,String email, String street,String city,String country,String jobTitle,String department,LocalDate employmentDate,String status,
                           String phone,Integer projects, Integer salary,Integer experience);
   // List<Employee>findAllByCompanyName(String company_name);

    List<Employee>listAll();
    void  deleteById(Long id);








}
