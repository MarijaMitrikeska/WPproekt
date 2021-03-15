package wp.com.demo.web.controller;


import com.sun.xml.bind.v2.TODO;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import wp.com.demo.model.Company;
import wp.com.demo.repository.CompanyRepository;
import wp.com.demo.service.CompanyService;

import java.util.List;

@Controller
@RequestMapping("/manage-companies")
public class CompanyListController {
    private final CompanyRepository companyRepository;
    private final CompanyService companyService;

    public CompanyListController(CompanyRepository companyRepository, CompanyService companyService) {
        this.companyRepository = companyRepository;
        this.companyService = companyService;
    }


    @GetMapping
    public String getCompaniesPage(Model model){
        List<Company> companies=this.companyService.listCompanies();
        model.addAttribute("companies", companies);
        model.addAttribute("bodyContent","companies-list");
        return "master-template";

    }

    // TODO : Should delete the company and its employees with their data
    @PostMapping("/delete/{id}")
    public String deleteCompany(@PathVariable Long id){
        this.companyService.deleteById(id);
        return "redirect:/manage-companies";
    }

    @GetMapping("/add-company")
    public String addCompanyPage(Model model){
        model.addAttribute("bodyContent","add-company");
        return "master-template";
    }

    @PostMapping("/add")
            public String saveCompany(
                    //@RequestParam(required = false)Long id,
                    @RequestParam String name,
                    @RequestParam String description,
                    @RequestParam String moto,
                    @RequestParam String owner,
                    @RequestParam Integer numEmployee,
                    @RequestParam Integer numInterns)
    {
       /*if (id!=null){
            this.companyService.edit(id,name,description, moto,owner,numEmployee,numInterns);
        }
        else {*/
            this.companyService.save(name,description,moto,owner,numEmployee,numInterns);

        return "redirect:/manage-companies";


    }

}
