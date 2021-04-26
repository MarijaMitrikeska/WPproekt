package wp.com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wp.com.demo.model.Company;
import wp.com.demo.model.Intern;

import java.util.List;

@Repository
public interface InterRepository extends JpaRepository<Intern,Long> {

    void deleteById(Long id);

    void deleteByCompanyId(Company company);

    List<Intern>findAllByCompanyId(Company company);
}
