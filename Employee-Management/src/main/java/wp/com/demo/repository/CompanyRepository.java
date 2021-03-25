package wp.com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wp.com.demo.model.Company;
import wp.com.demo.model.User;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {

void deleteByName(String name);
Optional<Company>findByCompanyUsername(String user);

//    Optional<Company> findById(Long id);
    boolean existsById(Long id);


}
