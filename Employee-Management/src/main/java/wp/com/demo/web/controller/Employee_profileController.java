package wp.com.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee-profile")
public class Employee_profileController {

    @GetMapping
    public String getProfilePage(Model model){

        model.addAttribute("bodyContent","employee-profile");
        return "master-template";
    }
}
