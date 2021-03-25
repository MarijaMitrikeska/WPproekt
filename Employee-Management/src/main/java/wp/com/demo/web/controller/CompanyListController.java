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


    private final CompanyRepository companyRepository;
    private final CompanyService companyService;
    private final UserService userService;
    private final EmployeeService employeeService;

    public CompanyListController(CompanyRepository companyRepository, CompanyService companyService, UserService userService, EmployeeService employeeService) {
        this.companyRepository = companyRepository;
        this.companyService = companyService;
        this.userService = userService;
        this.employeeService = employeeService;
    }


    @GetMapping
    public String getCompaniesPage(Model model) {
        List<Company> companies = this.companyService.listCompanies();
        model.addAttribute("companies", companies);
        model.addAttribute("bodyContent", "companies-list");
        return "master-template";

    }

    // TODO : Should delete the company and its employees with their data
    @DeleteMapping("/delete/{id}")
    public String deleteCompany(@PathVariable Long id) {
        Company company=this.companyService.findById(id).get();
    List<Employee> employees=this.employeeService.listByCompanyId(company);
        this.companyService.deleteById(id);
//        this.employeeService.deleteEmployeesByCompany(employees);
//        this.employeeService.listByCompanyId(company).removeAll(Collections.emptyList());

        this.companyService.listEmployeesInCompany(id).removeAll(Collections.emptyList());
        return "redirect:/manage-companies";
    }


    @GetMapping("/add-company")
    public String addCompanyPage(Model model) {
        model.addAttribute("bodyContent", "add-company");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveCompany(
            //@RequestParam(required = false)Long id,
            HttpServletRequest request,
            Authentication authentication,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String moto,
            @RequestParam String owner,
            @RequestParam Integer numEmployee,
            @RequestParam Integer numInterns,
            @RequestParam("image") MultipartFile profilePicture
    ) throws IOException {
        String username=request.getRemoteUser();



        if (!profilePicture.isEmpty()) {
            File picture_target = new File(profilePicture.getOriginalFilename());
//                    (targetFolderImagePPPath + request.getRemoteUser() + "." + profilePicture.getOriginalFilename().split("\\.")[1]);
            if (picture_target.exists()) {
                picture_target.delete();

            }
            profilePicture.transferTo(picture_target);
            this.companyService.save(username,name, description, moto, owner, profilePicture, "../ProfilePictures/" + picture_target.getName(), numEmployee, numInterns);

        }

       /*if (id!=null){
            this.companyService.edit(id,name,description, moto,owner,numEmployee,numInterns);
        }*/
        else {
            this.companyService.save(username,name, description, moto, owner, profilePicture, "../ProfilePictures/", numEmployee, numInterns);


        }
        return "redirect:/manage-companies";

    }
}
