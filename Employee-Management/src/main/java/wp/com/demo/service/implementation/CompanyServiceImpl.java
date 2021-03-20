package wp.com.demo.service.implementation;



import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import wp.com.demo.model.Company;
import wp.com.demo.model.Employee;
import wp.com.demo.model.User;
import wp.com.demo.repository.CompanyRepository;
import wp.com.demo.repository.UserRepository;
import wp.com.demo.service.CompanyService;
import wp.com.demo.service.EmployeeService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl  implements CompanyService {
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final EmployeeService employeeService;

    public CompanyServiceImpl(CompanyRepository companyRepository, UserRepository userRepository, EmployeeService employeeService) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
        this.employeeService = employeeService;
    }

//    @Override
//    public Company addCompany(String name, String description,String moto, String owner,String imageSource, Integer employee, Integer interns) {
//        /*if (name==null || name.isEmpty()){
//            throw new IllegalArgumentException();
//        }
//        if (this.companyRepository.existsById()){
//            throw new IllegalArgumentException(); // nov excp CompanyAlreadyExists
//        }*/
//        Company company=new Company(name,description,moto,owner,imageSource,employee,interns);
//        companyRepository.save(company);
//        return company;
//
//    }

    @Override
    public Company update(String name, String description, String moto, String owner, MultipartFile profilePicture, String imageSource, Integer employee, Integer interns) {
        if (name==null || name.isEmpty()){
            throw new IllegalArgumentException();
        }
        Company company=new Company(name,description,moto,owner,imageSource,employee,interns);
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
    public List<Company> listEmployees() {
        return null;
    }

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
    public Optional<Company> edit(Long id, String name, String desc ,String owner, String moto, MultipartFile profilePicture,String imageSource, Integer numEm,Integer numInt) {
       Company company=this.companyRepository.findById(id).orElseThrow(IllegalArgumentException::new);
   company.setName(name);
   company.setDescription(desc);
   company.setMoto(moto);
   company.setEmployee_num(numEm);
   company.setIntern_num(numInt);
   company.setOwner(owner);
//   company.setImageSource(imageSource);
   if (!profilePicture.isEmpty()) company.setImageSource(imageSource);

   return Optional.of(this.companyRepository.save(company));

    }

    @Override
    public Optional<Company> save(String name, String desc ,String owner, String moto,  MultipartFile profilePicture,String imageSource,Integer numEm, Integer numInt) {
      return Optional.of(this.companyRepository.save(new Company(name,desc,owner,moto,imageSource,numEm,numInt)));
    }

    @Override
    public Optional<Company> findById(Long id) {
        return this.companyRepository.findById(id);
    }

    @Override
    public Company addEmployeeToCompany(String username, Long employeeId) {
        Company company=this.getCompany(username).orElseThrow(()->new IllegalArgumentException());
        Employee employee=this.employeeService.findById(employeeId)
                .orElseThrow(()->new IllegalArgumentException());
        if (company.getEmployees()
        .stream().filter(i->i.getId().equals(employeeId))
        .collect(Collectors.toList()).size()>0)
            throw new IllegalArgumentException();
        company.getEmployees().add(employee);
        return this.companyRepository.save(company);

    }

    @Override
    public Optional<Company> getCompany(String username) {
        User user=this.userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));
        return this.companyRepository.findByUser(user);
//                .orElseGet(()->{
//            Company company=new Company(user);
//        })
    }

    @Override
    public List<Employee> listEmployeesInCompany(Long companyId) {
        if (this.companyRepository.findById(companyId).isPresent())
            return this.companyRepository.findById(companyId).get().getEmployees();
//        else throw new CompanyNotFountException(companyId);
          else throw new IllegalArgumentException();
    }
}
