package wp.com.demo.web.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wp.com.demo.model.Company;
import wp.com.demo.model.Employee;
import wp.com.demo.model.Intern;
import wp.com.demo.service.CompanyService;
import wp.com.demo.service.EmployeeService;
import wp.com.demo.service.InternService;
import wp.com.demo.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class CompanyEmployeesAndInternsController {

    private final CompanyService companyService;
    private final EmployeeService employeeService;
    private final InternService internService;

    public CompanyEmployeesAndInternsController(CompanyService companyService, EmployeeService employeeService, InternService internService) {
        this.companyService = companyService;
        this.employeeService = employeeService;
        this.internService = internService;
    }



    @GetMapping("/{id}")
    public String getEmployees_InternsPage(@PathVariable Long id, Model model) {
        if (this.companyService.findById(id).isPresent()) {

            Company company = this.companyService.findById(id).get();
            model.addAttribute("company", company);
            List<Employee> employees = this.employeeService.listByCompanyId(company);
            List<Intern>interns=this.internService.listByCompanyId(company);
            model.addAttribute("employees", employees);
            model.addAttribute("interns",interns);
            model.addAttribute("bodyContent", "Company-employees");
            return "master-template";
        }

        return "redirect:/home";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id,HttpServletRequest request) {

        String username=request.getRemoteUser();
        Long companyId =this.companyService.getCompany(username).get().getId();
        this.employeeService.deleteById(id);
        return "redirect:/employees/"+companyId;
    }

    @DeleteMapping("/deleteIntern/{id}")
    public String deleteIntern(@PathVariable Long id,HttpServletRequest request) {
        String username=request.getRemoteUser();
        Long companyId =this.companyService.getCompany(username).get().getId();
        this.internService.deleteById(id);
        return "redirect:/employees/"+companyId;
    }



    @GetMapping("/add-employee/{id}")
    public String addEmployee(@PathVariable Long id, Model model) {

        if (this.companyService.findById(id).isPresent()) {
            Company company = this.companyService.findById(id).get();
            model.addAttribute("company", company);

//            Employee employee = this.employeeService.findById(id).get();
//                model.addAttribute("employee", employee);
                model.addAttribute("bodyContent", "add-employee");
        }

        return "master-template";

    }
    @GetMapping("/add-intern/{id}")
    public String addIntern(@PathVariable Long id, Model model) {


        if (this.companyService.findById(id).isPresent()) {
            Company company = this.companyService.findById(id).get();
            model.addAttribute("company", company);

            if (this.internService.findById(id).isPresent()) {
                Intern intern = this.internService.findById(id).get();
                model.addAttribute("intern", intern);
                model.addAttribute("bodyContent", "addIntern");


            }
            else {
                model.addAttribute("bodyContent", "addIntern");
            }

        }

        return "master-template";

    }

    @PostMapping("/addIntern/{id}")
    public String saveIntern(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String embg,
            @RequestParam String email,
            @RequestParam String street,
            @RequestParam String city,
            @RequestParam String country,
            @RequestParam String internship_section,
            @RequestParam String department,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate internship_start,
            @RequestParam String intership_duration,
            @RequestParam String phone,
            @RequestParam Integer projects,
            @RequestParam Integer internship_salary,
            @RequestParam Integer experience,
            @RequestParam("personImage") MultipartFile profilePicture) throws IOException {

            Company company = this.companyService.findById(id).get();

            if (!profilePicture.isEmpty()) {
                File picture_target = new File(profilePicture.getOriginalFilename());
//                    (targetFolderImagePPPath + request.getRemoteUser() + "." + profilePicture.getOriginalFilename().split("\\.")[1]);
                if (picture_target.exists()) {
                    picture_target.delete();

                }

                profilePicture.transferTo(picture_target);
              this.internService.save(name,surname,embg,email,street,city,country,internship_section,department,internship_start,intership_duration,phone,
                      projects,internship_salary,experience,profilePicture,"../ProfilePictures/" +picture_target.getName(),company);




            } else {


                this.internService.save(name,surname,embg,email,street,city,country,internship_section,department,internship_start,intership_duration,phone,
                        projects,internship_salary,experience,profilePicture,"../ProfilePictures/",company);

            }


            return"redirect:/employees/{id}";
}





    @PostMapping("/add/{id}")
    public String saveEmployee(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String embg,
            @RequestParam String email,
            @RequestParam String street,
            @RequestParam String city,
            @RequestParam String country,
            @RequestParam String jobTitle,
            @RequestParam String department,

            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate employmentDate,
            @RequestParam String status,
            @RequestParam String phone,
            @RequestParam Integer projects,
            @RequestParam Integer salary,
            @RequestParam Integer experience,
            @RequestParam("personImage") MultipartFile profilePicture) throws IOException {

        Company company = this.companyService.findById(id).get();



        if (!profilePicture.isEmpty()) {
            File picture_target = new File(profilePicture.getOriginalFilename());
//                    (targetFolderImagePPPath + request.getRemoteUser() + "." + profilePicture.getOriginalFilename().split("\\.")[1]);
            if (picture_target.exists()) {
                picture_target.delete();

            }

            profilePicture.transferTo(picture_target);
            this.employeeService.save(company, name, surname, profilePicture, "../ProfilePictures/" +picture_target.getName(), embg, email, street, city, country, jobTitle, department, employmentDate, status,
                    phone, projects, salary, experience);




        } else {
            this.employeeService.save(company, name, surname, profilePicture, "../ProfilePictures/", embg, email, street, city, country, jobTitle, department, employmentDate, status,
                    phone, projects, salary, experience);



        }


        return"redirect:/employees/{id}";
    }




}



