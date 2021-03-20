package wp.com.demo.web.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wp.com.demo.model.Company;
import wp.com.demo.model.Employee;
import wp.com.demo.repository.EmployeeRepository;
import wp.com.demo.service.CompanyService;
import wp.com.demo.service.EmployeeService;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/employee-profile")
public class EmployeeProfileController {

    public static final String targetFolderImagePPPath = "C:\\Users\\PC\\Desktop\\Employee-managament-app\\WPproekt\\Employee-Management\\target\\classes\\static\\ProfilePictures";

    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;
    private final CompanyService companyService;

    public EmployeeProfileController(EmployeeService employeeService, EmployeeRepository employeeRepository, CompanyService companyService) {
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
        this.companyService = companyService;
    }


    @GetMapping("/{id}")
    public String getEmployeeProfilePage(@PathVariable Long id, Model model) {
        if (this.employeeService.findById(id).isPresent()) {
            Employee employee = this.employeeService.findById(id).get();
            model.addAttribute("employee", employee);

            model.addAttribute("bodyContent", "employee-profile");
            return "master-template";

        }

        return "redirect:/home";

    }


//TODO: da gi podelam add i edit formite za dodavanje

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

    @GetMapping("/add-employee/{id}")
    public String addEmployee(@PathVariable Long id, Model model) {

        if (this.companyService.findById(id).isPresent()) {
            Company company = this.companyService.findById(id).get();

            model.addAttribute("company", company);
            model.addAttribute("bodyContent", "add-employee");
            return "master-template";
        }
        return "redirect:/employee?errror=EmployeeNotFound";

    }

    @PostMapping("/add/{id}")
    public String saveEmployee(
            @PathVariable("id") Long id,
            Model model,

//          @RequestParam(required = false) Long id1,
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
        if (this.companyService.findById(id).isPresent()) {
            Company company = this.companyService.findById(id).get();

            model.addAttribute("company", company);

            if (this.employeeService.findById(id).isPresent()) {
                Employee employee = this.employeeService.findById(id).get();
                model.addAttribute("employee", employee);

                if (!profilePicture.isEmpty()) {
                    File picture_target = new File(profilePicture.getOriginalFilename());
//                    (targetFolderImagePPPath + request.getRemoteUser() + "." + profilePicture.getOriginalFilename().split("\\.")[1]);
                    if (picture_target.exists()) {
//                    picture_target.delete();

                    }
                    if (id != null) {
                        this.employeeService.edit(id, name, surname, profilePicture, "../ProfilePictures/", embg, email, street, city, country, jobTitle, department, employmentDate, status,
                                phone, projects, salary, experience);
                    }

                }
            } else {

                this.employeeService.save(name, surname, profilePicture, "../ProfilePictures/", embg, email, street, city, country, jobTitle, department, employmentDate, status,
                        phone, projects, salary, experience);
            }
        }


            return "redirect:/employee-profile/{id}";
        }
    }

            //return "redirect:/home";

            //Create a DateTimeFormatter with your required format:
//        DateTimeFormatter dateTimeFormat = new DateTimeFormatter(DateTimeFormatter.BASIC_ISO_DATE);
//
//
//        //Next parse the date from the @RequestParam, specifying the TO type as
//        //TemporalQuery:
//        LocalDateTime date = dateTimeFormat.parse(employmentDate, LocalDateTime::from);

//        if (id!=null){
//            this.employeeService.edit(id,name, surname, embg, email,street, city, country, jobTitle, department, employmentDate,status,
//                    phone, projects, salary, experience);
//        }
//        else {
//            this.employeeService.save(name, surname, embg, email,street, city, country, jobTitle, department, employmentDate,status,
//                    phone, projects, salary, experience);
//        }
//        return "redirect:/employees/{id}";



//                if (id != null) {
//                    profilePicture.transferTo(picture_target);
//                    this.employeeService.edit(id, name, surname, profilePicture, "../ProfilePictures/" + picture_target.getName(), embg, email, street, city, country, jobTitle, department, employmentDate, status,
//                            phone, projects, salary, experience);
////                return "redirect:/manage-companies";
//                }







