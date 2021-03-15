package wp.com.demo.service.implementation;



import org.springframework.stereotype.Service;
import wp.com.demo.model.Company;
import wp.com.demo.repository.CompanyRepository;
import wp.com.demo.service.CompanyService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl  implements CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company addCompany(String name, String description,String moto, String owner, Integer employee, Integer interns) {
        /*if (name==null || name.isEmpty()){
            throw new IllegalArgumentException();
        }
        if (this.companyRepository.existsById()){
            throw new IllegalArgumentException(); // nov excp CompanyAlreadyExists
        }*/
        Company company=new Company(name,description,moto,owner,employee,interns);
        companyRepository.save(company);
        return company;

    }

    @Override
    public Company update(String name, String description, String moto,String owner, Integer employee, Integer interns) {
        if (name==null || name.isEmpty()){
            throw new IllegalArgumentException();
        }
        Company company=new Company(name,description,moto,owner,employee,interns);
        companyRepository.save(company);
        return company;

    }

   @Override
    public void delete(String name) {
        /*if (name==null || name.isEmpty()){
            throw new IllegalArgumentException();
        }
        companyRepository.deleteByCompany_name(name);*/

    }

    @Override
    public void deleteById(Long id) {
        this.companyRepository.deleteById(id);

    }

    @Override
    public List<Company> listEmployees(String name) {
        //return companyRepository.findAllByCompany_name(name);
        return null;
    }

    @Override
    public List<Company> searchEmployees(String search) {
        return null;
    }

    @Override
    public List<Company> listCompanies() {
        return this.companyRepository.findAll();
    }




    @Override
    @Transactional
    public Optional<Company> edit(Long id, String name, String desc ,String owner, String moto, Integer numEm,Integer numInt) {
       Company company=this.companyRepository.findById(id).orElseThrow(IllegalArgumentException::new);
   company.setName(name);
   company.setDescription(desc);
   company.setMoto(moto);
   company.setEmployee_num(numEm);
   company.setIntern_num(numInt);
   company.setOwner(owner);
   return Optional.of(this.companyRepository.save(company));

    }

    @Override
    public Optional<Company> save(String name, String desc ,String owner, String moto, Integer numEm, Integer numInt) {
      return Optional.of(this.companyRepository.save(new Company(name,desc,owner,moto,numEm,numInt)));
    }

    @Override
    public Optional<Company> findById(Long id) {
        return this.companyRepository.findById(id);
    }
}
