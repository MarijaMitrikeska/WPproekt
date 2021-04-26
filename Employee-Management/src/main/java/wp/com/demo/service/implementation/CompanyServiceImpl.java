package wp.com.demo.service.implementation;




import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import wp.com.demo.model.Company;
import wp.com.demo.model.Employee;

import wp.com.demo.model.exceptions.CompanyNotFoundException;
import wp.com.demo.model.exceptions.EmployeesNotFound;
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
    public void deleteById(Long id) {

        this.companyRepository.deleteById(id);
    }

//    @Override
//    public List<Company> listEmployees() {
//        return null;
//    }

//    @Override
//    public List<Company> listEmployees(String name) {
////        return companyRepository.findAllByCompany_name(name);
//        return null;
//    }

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
    public Optional<Company> edit(String user,Long id, String name, String desc ,String owner, String moto, MultipartFile profilePicture,String imageSource, Integer numEm,Integer numInt) {
       Company company=this.companyRepository.findById(id).orElseThrow( ()-> new CompanyNotFoundException(id));

   company.setName(name);
   company.setDescription(desc);
   company.setMoto(moto);
   company.setEmployee_num(numEm);
   company.setIntern_num(numInt);
   company.setOwner(owner);

   if (!profilePicture.isEmpty()) company.setImageSource(imageSource);

   return Optional.of(this.companyRepository.save(company));

    }

    @Override
    public Optional<Company> save(String  user,String name, String desc ,String owner, String moto,  MultipartFile profilePicture,String imageSource,Integer numEm, Integer numInt) {
      return Optional.of(this.companyRepository.save(new Company(user,name,desc,owner,moto,imageSource,numEm,numInt)));
    }

    @Override
    public Optional<Company> findById(Long id) {

        return this.companyRepository.findById(id);
    }




    @Override
    public Optional<Company> getCompany(String username) {

        return this.companyRepository.findByCompanyUsername(username);

    }


    @Override
    public List<Employee> listEmployeesInCompany(Long companyId) {
        if (this.companyRepository.findById(companyId).isPresent()) {
            if (this.companyRepository.findById(companyId).get().getEmployees().size() > 0)
                return this.companyRepository.findById(companyId).get().getEmployees();
        }

          throw new EmployeesNotFound(companyId);
    }
}
