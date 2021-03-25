package wp.com.demo.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wp.com.demo.model.User;
import wp.com.demo.model.exceptions.InvalidCredentialsException;
import wp.com.demo.service.AuthenticationService;
import wp.com.demo.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final AuthenticationService  authenticationService;
    private final UserService userService;

    public LoginController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }


    @GetMapping
    public String getLoginPage(@RequestParam(required = false) String error,
                               @RequestParam(required = false) String success
                    ,Model model){

        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("errorMessage", error);
        }
        else if(success != null && !success.isEmpty()) {
            model.addAttribute("Success", true);
            model.addAttribute("successMessage", success);
        }
        model.addAttribute("bodyContent","login");
        return "master-template";
    }

    @PostMapping
    public String login(HttpServletRequest request, Model model){
        User user=null;
        try {
            user=this.authenticationService.login(request.getParameter("username"), request.getParameter("password"));
            request.getSession().setAttribute("user", user);
            return "redirect:/home";

        }
        catch (InvalidCredentialsException exception){
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());

            return "login";
        }
    }



}
