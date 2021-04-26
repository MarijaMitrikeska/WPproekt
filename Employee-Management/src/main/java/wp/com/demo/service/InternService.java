package wp.com.demo.service;

import org.springframework.web.multipart.MultipartFile;
import wp.com.demo.model.Company;
import wp.com.demo.model.Intern;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface InternService {

    Optional<Intern> findById(Long id);

    Optional <Intern>save( String name, String surname, String embg, String email, String street, String city, String country, String intership_section, String department, LocalDate intership_start, String intership_duration, String phone, Integer projects, Integer intership_salary, Integer experience,MultipartFile profilePicture, String imageSource, Company companyId);
    Optional<Intern >edit(Long id, String name, String surname, String embg, String email, String street, String city, String country, String intership_section, String department, LocalDate intership_start, String intership_duration, String phone, Integer projects, Integer intership_salary, Integer experience,MultipartFile profilePicture, String imageSource, Company companyId);


    List<Intern >listAll();
    void  deleteById(Long id);

    void deleteByCompanyId(Company id);
    List<Intern>listByCompanyId(Company company);




}
