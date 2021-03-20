package wp.com.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import wp.com.demo.model.Company;
import wp.com.demo.model.Employee;
import wp.com.demo.repository.CompanyRepository;
import wp.com.demo.service.CompanyService;
import wp.com.demo.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class CompanyEmployeesController {

        private final CompanyService companyService;
        private final CompanyRepository companyRepository;
        private final EmployeeService employeeService;

    public CompanyEmployeesController(CompanyService companyService, CompanyRepository companyRepository, EmployeeService employeeService) {
        this.companyService = companyService;
        this.companyRepository = companyRepository;
        this.employeeService = employeeService;
    }
    //TODO: da stavam id spored koe ke se zacuvuvaat vrabotenite koi se dodavaat

    //@GetMapping("/{id}")
    @GetMapping("/{id}")
    public String getEmployeePage(@PathVariable Long id, Model model){
        if (this.companyService.findById(id).isPresent()){

        Company company=this.companyService.findById(id).get();
            model.addAttribute("company", company);
            List<Employee>employees=this.employeeService.listAll();
            model.addAttribute("employees",employees);
            model.addAttribute("bodyContent","Company-employees");
            return "master-template";
        }

        return "redirect:/home";
    }


}
