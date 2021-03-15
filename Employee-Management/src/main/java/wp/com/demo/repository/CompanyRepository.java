package wp.com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wp.com.demo.model.Company;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {
//Optional<Company> findByCompany_name(String company_name);
void deleteByName(String name);


//Optional<Company> findById(Long id);
    boolean existsById(Long id);
    //List<Company>findAllByCompany_name(String name);

}
