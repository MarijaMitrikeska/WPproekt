package wp.com.demo.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import wp.com.demo.model.Company;
import wp.com.demo.model.Employee;
import wp.com.demo.model.exceptions.EmployeeNotFoundException;
import wp.com.demo.model.exceptions.InvalidCredentialsException;

import wp.com.demo.repository.EmployeeRepository;

import wp.com.demo.service.EmployeeService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;


    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;

    }

    @Override
    public Optional<Employee> findById(Long id) {

        return this.employeeRepository.findById(id);
    }


    @Override
    @Transactional
    public Optional<Employee> save(Company companyId,String name, String surname,MultipartFile profilePicture,String imageSource, String embg, String email, String street, String city, String country, String jobTitle, String department, LocalDate employmentDate, String status,
                                   String phone, Integer projects, Integer salary, Integer experience) {

        return Optional.of(this.employeeRepository.save(new Employee(companyId,name, surname, imageSource,embg, email,street, city, country, jobTitle, department, employmentDate,status,
                phone, projects, salary, experience)));

    }



    @Override
    @Transactional
    public Optional<Employee> edit(Long id,Company companyId, String name, String surname, MultipartFile profilePicture, String imageSource, String embg, String email, String street, String city, String country, String jobTitle, String department, LocalDate employmentDate, String status,
                                   String phone, Integer projects, Integer salary, Integer experience) {
        Employee employee=this.employeeRepository.findById(id).orElseThrow(()-> new EmployeeNotFoundException(id));

        employee.setName(name);
        employee.setSurname(surname);
        employee.setEmbg(embg);
        employee.setEmail(email);
        employee.setStreet(street);
        employee.setCity(city);
        employee.setCountry(country);
        employee.setJobTitle(jobTitle);
        employee.setDepartment(department);
        employee.setEmploymentDate(employmentDate);
        employee.setStatus(status);
        employee.setPhone(phone);
        employee.setProjects(projects);
        employee.setSalary(salary);
        employee.setExperience(experience);
//        if (!profilePicture.isEmpty())
            employee.setImageSource(imageSource);

        return Optional.of(this.employeeRepository.save(employee));


    }

    @Override
    public void deleteById(Long id) {
        this.employeeRepository.deleteById(id);

    }

    @Override
    public void deleteByCompanyId(Company id) {
        this.employeeRepository.deleteByCompanyId(id);

    }

    @Override
    public void deleteEmployeesByCompany(List<Employee> employees) {

        this.employeeRepository.deleteAll(employees);
    }

    @Override
    public Employee findCompanyId(Company companyId) {

        return  this.employeeRepository.findByCompanyId(companyId);
    }

    @Override
    public List<Employee> listAll() {

        return this.employeeRepository.findAll();
    }

    @Override
    public List<Employee>listByCompanyId(Company company){
        return this.employeeRepository.findAllByCompanyId(company);
    }




}
