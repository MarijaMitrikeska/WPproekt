package wp.com.demo.service;

import wp.com.demo.model.Company;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

public interface CompanyService {
    Company addCompany(String name, String description,String moto, String owner, Integer employee, Integer interns);
    Company update(String name, String description,String moto, String owner, Integer employee, Integer interns);
    void delete(String name);
    void deleteById(Long id);
    List<Company>listEmployees(String name);
    List<Company>searchEmployees(String search);
    List<Company>listCompanies();

    Optional<Company>edit(Long id, String name, String desc,String owner, String moto, Integer numEm, Integer numInt);

    Optional<Company>save(String name, String desc,String owner, String moto, Integer numEm, Integer numInt);
    Optional<Company>findById(Long id);
}
