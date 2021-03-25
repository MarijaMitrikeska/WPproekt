package wp.com.demo.web.controller;

import org.dom4j.rule.Mode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wp.com.demo.model.Company;
import wp.com.demo.model.Employee;
import wp.com.demo.model.User;
import wp.com.demo.repository.CompanyRepository;
import wp.com.demo.service.CompanyService;
import wp.com.demo.service.EmployeeService;
import wp.com.demo.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class CompanyEmployeesController {

    private final CompanyService companyService;

    private final EmployeeService employeeService;
    private final UserService userService;

    public CompanyEmployeesController(CompanyService companyService,  EmployeeService employeeService, UserService userService) {
        this.companyService = companyService;

        this.employeeService = employeeService;
        this.userService = userService;
    }
    //TODO: da stavam id spored koe ke se zacuvuvaat vrabotenite koi se dodavaat


    @GetMapping("/{id}")
    public String getEmployeesPage(@PathVariable Long id, Model model) {
        if (this.companyService.findById(id).isPresent()) {


            Company company = this.companyService.findById(id).get();
            model.addAttribute("company", company);
            List<Employee> employees = this.employeeService.listByCompanyId(company);
            model.addAttribute("employees", employees);
            model.addAttribute("bodyContent", "Company-employees");
            return "master-template";
        }

        return "redirect:/home";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        Company company=this.companyService.findById(id).get();
        List<Employee> employees=this.employeeService.listByCompanyId(company);
        this.companyService.deleteById(id);
        this.employeeService.deleteEmployeesByCompany(employees);

        return "redirect:/manage-companies";
    }


    @GetMapping("/add-employee/{id}")
    public String addEmployee(@PathVariable Long id, Model model, HttpServletRequest request) {
//        String username=request.getRemoteUser();
//        User user=  this.userService.getUsername(username);

        if (this.companyService.findById(id).isPresent()) {
            Company company = this.companyService.findById(id).get();
            model.addAttribute("company", company);

            if (this.employeeService.findById(id).isPresent()) {
                Employee employee = this.employeeService.findById(id).get();
                model.addAttribute("employee", employee);
                model.addAttribute("bodyContent", "add-employee");
//                this.companyService.addEmployeeToCompany(user,employee.getId());

            }
            else {
                model.addAttribute("bodyContent", "add-employee");
            }

        }

        return "master-template";

    }

    @PostMapping("/add/{id}")
    public String saveEmployee(

            HttpServletRequest request,
            @PathVariable Long id,
//            @RequestParam(required = false) Long id1, samo se dodava emp
            Model model,
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String embg,
            @RequestParam String email,
            @RequestParam String street,
            @RequestParam String city,
            @RequestParam String country,
            @RequestParam String jobTitle,
            @RequestParam String department,
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)Date employmentDate ,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate employmentDate,
            @RequestParam String status,
            @RequestParam String phone,
            @RequestParam Integer projects,
            @RequestParam Integer salary,
            @RequestParam Integer experience,
            @RequestParam("personImage") MultipartFile profilePicture) throws IOException {
        String username=request.getRemoteUser();
//        User user=  this.userService.getUsername(username);
//
//        Company company=this.companyService.getCompany(user).orElseThrow(()->new UsernameNotFoundException(username));
//        Optional<Company> companyId=this.companyService.findById(company.getId());
//        if (this.companyService.findById(id).isPresent()) {
            Company company = this.companyService.findById(id).get();
//            model.addAttribute("company", company);


            if (!profilePicture.isEmpty()) {
                File picture_target = new File(profilePicture.getOriginalFilename());
//                    (targetFolderImagePPPath + request.getRemoteUser() + "." + profilePicture.getOriginalFilename().split("\\.")[1]);
                if (picture_target.exists()) {
                    picture_target.delete();

                }
//                if (id1 != null) {
//                    this.employeeService.edit(id1, company, name, surname, profilePicture, "../ProfilePictures/", embg, email, street, city, country, jobTitle, department, employmentDate, status,
//                            phone, projects, salary, experience);
//                }
                profilePicture.transferTo(picture_target);
              this.employeeService.save(company, name, surname, profilePicture, "../ProfilePictures/" +picture_target.getName(), embg, email, street, city, country, jobTitle, department, employmentDate, status,
                        phone, projects, salary, experience);
//                this.employeeService.addEmployeeToCompany(company,employee);



            } else {


                this.employeeService.save(company, name, surname, profilePicture, "../ProfilePictures/", embg, email, street, city, country, jobTitle, department, employmentDate, status,
                        phone, projects, salary, experience);



            }
//        }

            return"redirect:/employees/{id}";
}
}






