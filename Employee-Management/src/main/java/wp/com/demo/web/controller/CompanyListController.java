package wp.com.demo.web.controller;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wp.com.demo.model.Company;
import wp.com.demo.model.Employee;
import wp.com.demo.repository.CompanyRepository;
import wp.com.demo.service.CompanyService;
import wp.com.demo.service.EmployeeService;
import wp.com.demo.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/manage-companies")
public class CompanyListController {


    public static final String targetFolderImagePPPath = "C:\\Users\\PC\\Desktop\\Employee-managament-app\\WPproekt\\Employee-Management\\target\\classes\\static\\ProfilePictures";



    private final CompanyService companyService;

    public CompanyListController(CompanyService companyService) {

        this.companyService = companyService;


    }


    @GetMapping
    public String getCompaniesPage(Model model) {
        List<Company> companies = this.companyService.listCompanies();
        model.addAttribute("companies", companies);
        model.addAttribute("bodyContent", "companies-list");
        return "master-template";

    }


    @DeleteMapping("/delete/{id}")
    public String deleteCompany(@PathVariable Long id) {
        Company company=this.companyService.findById(id).get();

        this.companyService.deleteById(id);

        return "redirect:/manage-companies";
    }


    @GetMapping("/add-company")
    public String addCompanyPage(Model model) {

        model.addAttribute("bodyContent", "add-company");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveCompany(
            HttpServletRequest request,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String moto,
            @RequestParam String owner,
            @RequestParam Integer numEmployee,
            @RequestParam Integer numInterns,
            @RequestParam("image") MultipartFile profilePicture) throws IOException {
        String username=request.getRemoteUser();


        if (!profilePicture.isEmpty()) {
            File picture_target = new File(profilePicture.getOriginalFilename());

            if (picture_target.exists()) {
                picture_target.delete();

            }
            profilePicture.transferTo(picture_target);
            this.companyService.save(username,name, description, moto, owner, profilePicture, "../ProfilePictures/" + picture_target.getName(), numEmployee, numInterns);

        }

        else {
            this.companyService.save(username,name, description, moto, owner, profilePicture, "../ProfilePictures/", numEmployee, numInterns);


        }
        return "redirect:/manage-companies";

    }
}
