package wp.com.demo.web.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wp.com.demo.model.Company;
import wp.com.demo.model.Employee;
import wp.com.demo.model.Intern;
import wp.com.demo.repository.EmployeeRepository;
import wp.com.demo.repository.InterRepository;
import wp.com.demo.service.CompanyService;
import wp.com.demo.service.EmployeeService;
import wp.com.demo.service.InternService;
import wp.com.demo.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

@Controller
@RequestMapping("/intern-profile")

public class InternProfileController {

    public static final String targetFolderImagePPPath = "C:\\Users\\PC\\Desktop\\Employee-managament-app\\WPproekt\\Employee-Management\\target\\classes\\static\\ProfilePictures";

    private final InternService internService;

    private final CompanyService companyService;




    public InternProfileController(InternService internService, CompanyService companyService) {
        this.internService = internService;

        this.companyService = companyService;
    }



    @GetMapping("/{id}")
    public String getInternProfilePage(@PathVariable Long id, Model model) {
        if (this.internService.findById(id).isPresent()) {
            Intern intern = this.internService.findById(id).get();
            model.addAttribute("intern",intern);

            model.addAttribute("bodyContent", "internProfile");
            return "master-template";

        }

        return "redirect:/home";

    }




    @GetMapping("/edit/{id}")
    public String editIntern(@PathVariable Long id, Model model) {
        if (this.internService.findById(id).isPresent()) {
            Intern intern = this.internService.findById(id).get();
            model.addAttribute("intern", intern);

            model.addAttribute("bodyContent", "addIntern");
            return "master-template";
        }
        return "redirect:/employee?errror=EmployeeNotFound";
    }



    @PostMapping("/add/{id}")
    public String saveIntern(
            @PathVariable Long id,
            Model model,
            HttpServletRequest request,

//            @RequestParam Long id,
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
        Intern intern=this.internService.findById(id).get();
        Long idCompany=intern.getCompanyId().getId();
        Company company = this.companyService.findById(idCompany).get();

        if (!profilePicture.isEmpty()) {
            File picture_target = new File(profilePicture.getOriginalFilename());
//                    (targetFolderImagePPPath + request.getRemoteUser() + "." + profilePicture.getOriginalFilename().split("\\.")[1]);
            if (picture_target.exists()) {
                picture_target.delete();

            }
            if (id != null) {
                this.internService.edit(id,name,surname,embg,email,street,city,country,internship_section,department,internship_start,intership_duration,phone,
                        projects,internship_salary,experience,profilePicture,"../ProfilePictures/" +picture_target.getName(),company);
            }

        }
        else {

            this.internService.save(name,surname,embg,email,street,city,country,internship_section,department,internship_start,intership_duration,phone,
                    projects,internship_salary,experience,profilePicture,"../ProfilePictures/",company);
        }
        return "redirect:/intern-profile/{id}";




    }
}
