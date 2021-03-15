package wp.com.demo.web.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import wp.com.demo.model.Employee;
import wp.com.demo.repository.EmployeeRepository;
import wp.com.demo.service.EmployeeService;

import java.time.LocalDateTime;
import java.util.Date;

@Controller
@RequestMapping("/employee-profile")
public class EmployeeProfileController {
    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;

    public EmployeeProfileController(EmployeeService employeeService, EmployeeRepository employeeRepository) {
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
    }


    @GetMapping
    public String getEmployeeProfilePage(Model model){

        model.addAttribute("bodyContent","employee-profile");
        return "master-template";
    }



    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable Long id, Model model){
        if (this.employeeService.findById(id).isPresent()){
            Employee employee=this.employeeService.findById(id).get();

            model.addAttribute("bodyContent","add-employee");
            return "master-template";
        }
        return "redirect:/employee?errror=EmployeeNotFound";
    }

    @GetMapping("/add-employee")
    public String addEmployee(Model model){


        model.addAttribute("bodyContent","add-employee");
        return "master-template";

    }
    @PostMapping("/add")
    public String saveEmployee(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String embg,
            @RequestParam String email,
            @RequestParam String street,
            @RequestParam String city,
            @RequestParam String country,
            @RequestParam String jobTitle,
            @RequestParam String department,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)Date employmentDate ,
            @RequestParam String status,
            @RequestParam String phone,
            @RequestParam Integer projects,
            @RequestParam Integer salary,
            @RequestParam Integer experience)



    {
        if (id!=null){
            this.employeeService.edit(id,name, surname, embg, email,street, city, country, jobTitle, department, employmentDate,status,
                    phone, projects, salary, experience);
        }
        else {
            this.employeeService.save(name, surname, embg, email,street, city, country, jobTitle, department, employmentDate,status,
                    phone, projects, salary, experience);
        }
        return "redirect:/employees/{id}";
    }


}
