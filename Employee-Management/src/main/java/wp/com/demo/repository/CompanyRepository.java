package wp.com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wp.com.demo.model.Company;

public interface CompanyRepository extends JpaRepository<Company,String> {


}
