package wp.com.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import wp.com.demo.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = {"/", "/home"})
public class HomeController {
    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getHomePage(HttpServletRequest request,Model model) {


        if (request.getRemoteUser()!=null && this.userService.findByUsername(request.getRemoteUser()).isPresent()){
            model.addAttribute("username", request.getRemoteUser());
        }
        return "home";
    }

}
