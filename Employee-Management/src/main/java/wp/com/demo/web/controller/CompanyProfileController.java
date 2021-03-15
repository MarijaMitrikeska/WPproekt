package wp.com.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import wp.com.demo.model.Company;
import wp.com.demo.repository.CompanyRepository;
import wp.com.demo.service.CompanyService;

@Controller
@RequestMapping("/company-profile")
public class CompanyProfileController {
    private final CompanyService companyService;
    private final CompanyRepository companyRepository;

    public CompanyProfileController(CompanyService companyService, CompanyRepository companyRepository) {
        this.companyService = companyService;
        this.companyRepository = companyRepository;
    }

    @GetMapping("/{id}")
    public String getCompaniesPage(@PathVariable Long id, Model model) {
        if (this.companyService.findById(id).isPresent()) {
            Company company = this.companyService.findById(id).get();
            model.addAttribute("company", company);
            model.addAttribute("bodyContent", "companyProfile");

            return "master-template";
        }
        return "redirect/:company?error=CompanyNotFound";


        //return "courses";
    }

    @GetMapping("/edit/{id}")
    public String editCompanyProfilePage(@PathVariable Long id, Model model) {
        if (this.companyService.findById(id).isPresent()) {
            Company company = this.companyService.findById(id).get();
            model.addAttribute("company", company);
            model.addAttribute("bodyContent", "edit-company");

            return "master-template";
        }
        return "redirect/:company?error=CompanyNotFound";
    }

    @PostMapping("/add")
    public String saveCompany(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String moto,
            @RequestParam String owner,
            @RequestParam Integer numEmployee,
            @RequestParam Integer numInterns) {
        if (id != null) {
            this.companyService.edit(id, name, description, moto, owner, numEmployee, numInterns);
        } else {
            this.companyService.save(name, description, moto, owner, numEmployee, numInterns);
        }
            return "redirect:/manage-companies";

    }
}
