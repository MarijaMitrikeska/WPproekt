package wp.com.demo.web.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wp.com.demo.model.Company;
import wp.com.demo.model.Employee;
import wp.com.demo.model.User;
import wp.com.demo.repository.EmployeeRepository;
import wp.com.demo.service.CompanyService;
import wp.com.demo.service.EmployeeService;
import wp.com.demo.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/employee-profile")
public class EmployeeProfileController {

    public static final String targetFolderImagePPPath = "C:\\Users\\PC\\Desktop\\Employee-managament-app\\WPproekt\\Employee-Management\\target\\classes\\static\\ProfilePictures";

    private final EmployeeService employeeService;

    private final CompanyService companyService;


    public EmployeeProfileController(EmployeeService employeeService,CompanyService companyService) {
        this.employeeService = employeeService;
        this.companyService = companyService;

    }


    @GetMapping("/{id}")
    public String getEmployeeProfilePage(@PathVariable Long id, Model model) {
        if (this.employeeService.findById(id).isPresent()) {
            Employee employee = this.employeeService.findById(id).get();
            model.addAttribute("employee", employee);

            model.addAttribute("bodyContent", "employee-profile");


        }
        return "master-template";



    }




    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable Long id, Model model) {
        if (this.employeeService.findById(id).isPresent()) {
            Employee employee = this.employeeService.findById(id).get();
            model.addAttribute("employee", employee);

            model.addAttribute("bodyContent", "add-employee");
            return "master-template";
        }
        return "redirect:/employee?errror=EmployeeNotFound";
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
        Employee employee=this.employeeService.findById(id).get();
        Long idCompany=employee.getCompanyId().getId();
        Company company = this.companyService.findById(idCompany).get();

                if (!profilePicture.isEmpty()) {
                    File picture_target = new File(profilePicture.getOriginalFilename());
//                    (targetFolderImagePPPath + request.getRemoteUser() + "." + profilePicture.getOriginalFilename().split("\\.")[1]);
                    if (picture_target.exists()) {
                    picture_target.delete();

                    }
                    if (id != null) {
                        this.employeeService.edit(id,company, name, surname, profilePicture, "../ProfilePictures/"+picture_target.getName(), embg, email, street, city, country, jobTitle, department, employmentDate, status,
                                phone, projects, salary, experience);
                    }

                }
             else {

                this.employeeService.save(company,name, surname, profilePicture, "../ProfilePictures/", embg, email, street, city, country, jobTitle, department, employmentDate, status,
                        phone, projects, salary, experience);
            }
            return "redirect:/employee-profile/{id}";




    }

}








