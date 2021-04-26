package wp.com.demo.service.implementation;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import wp.com.demo.model.Company;
import wp.com.demo.model.Employee;
import wp.com.demo.model.Intern;
import wp.com.demo.model.exceptions.InvalidCredentialsException;
import wp.com.demo.repository.InterRepository;
import wp.com.demo.service.InternService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class InternServiceImpl implements InternService {

    private final InterRepository interRepository;

    public InternServiceImpl(InterRepository interRepository) {
        this.interRepository = interRepository;
    }

    @Override
    public Optional<Intern> findById(Long id) {

        return this.interRepository.findById(id);
    }




    @Override
    public Optional<Intern> save(String name, String surname, String embg, String email, String street, String city, String country, String intership_section, String department, LocalDate intership_start, String intership_duration, String phone, Integer projects, Integer intership_salary, Integer experience,MultipartFile profilePicture, String imageSource, Company companyId) {
        return Optional.of(this.interRepository.save(new Intern(name,surname,embg,email,street,city,country,intership_section,department,intership_start,intership_duration,phone,projects,intership_salary,experience,imageSource,companyId)));
    }

    @Override
    public Optional<Intern> edit(Long id, String name, String surname, String embg, String email, String street, String city, String country, String intership_section, String department, LocalDate intership_start, String intership_duration, String phone, Integer projects, Integer intership_salary, Integer experience,MultipartFile profilePicture, String imageSource, Company companyId) {

        Intern intern=this.interRepository.findById(id).orElseThrow(InvalidCredentialsException::new);
        intern.setName(name);
        intern.setSurname(surname);
        intern.setEmbg(embg);
        intern.setEmail(email);
        intern.setStreet(street);
        intern.setCity(city);
        intern.setCountry(country);
        intern.setIntership_section(intership_section);
        intern.setDepartment(department);
        intern.setIntership_duration(intership_duration);
        intern.setIntership_start(intership_start);

        intern.setPhone(phone);
        intern.setProjects(projects);
        intern.setIntership_salary(intership_salary);
        intern.setExperience(experience);
//        if (!profilePicture.isEmpty())
        intern.setImageSource(imageSource);
        return Optional.of(this.interRepository.save(intern));
    }

    @Override
    public List<Intern> listAll() {
        return this.interRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        this.interRepository.deleteById(id);
    }




    @Override
    public void deleteByCompanyId(Company id) {
        this.interRepository.deleteByCompanyId(id);

    }

    @Override
    public List<Intern>listByCompanyId(Company company){
        return this.interRepository.findAllByCompanyId(company);
    }


}
