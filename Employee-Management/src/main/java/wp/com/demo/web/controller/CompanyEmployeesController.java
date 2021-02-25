package wp.com.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employees")
public class CompanyEmployeesController {
    @GetMapping
    public String getProfilePage(Model model){

        model.addAttribute("bodyContent","Company-employees");
        return "master-template";
    }


}
