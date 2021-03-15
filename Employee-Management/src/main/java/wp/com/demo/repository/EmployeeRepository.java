package wp.com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wp.com.demo.model.Employee;
import wp.com.demo.model.User;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

   // Optional<Employee> findByCompany_work(String company);
   /* Optional<Employee>findById(Long id);
    boolean existsByCompany_workAndId(String company, Long id);
    boolean existsByEmployee_Embg(String embg);*/
    void deleteById(Long id);
    //void deleteByEmployee_name(String employee_name);




}
