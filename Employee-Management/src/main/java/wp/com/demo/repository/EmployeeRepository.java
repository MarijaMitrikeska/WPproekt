package wp.com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wp.com.demo.model.Company;
import wp.com.demo.model.Employee;
import wp.com.demo.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {


    void deleteById(Long id);

    Employee findByCompanyId(Company companyId);

    void  deleteByCompanyId(Company company);

    List<Employee> findAllByCompanyId(Company company);

//    List<Ad> getAdsByCompany_Id(String id);
//
//    List<Ad> findByTimestampContainingOrHeaderContainingIgnoreCaseOrCompany_NameContainingIgnoreCase(String timestamp, String header, String companyName);

List<Employee>findByNameContaining(String name);
}
