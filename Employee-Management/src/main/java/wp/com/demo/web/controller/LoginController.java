package wp.com.demo.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wp.com.demo.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {


    private final UserService userService;

    public LoginController(UserService userService) {

        this.userService = userService;
    }


    @GetMapping
    public String getLoginPage(@RequestParam(required = false) String error,
                               @RequestParam(required = false) String success, Model model,
                               HttpServletRequest request) {

        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("errorMessage", error);
        }
        if (success != null && !success.isEmpty()) {
            model.addAttribute("Success", true);
            model.addAttribute("successMessage", success);
        }

        if (request.getRemoteUser() != null) {
            this.userService.getUsername(request.getRemoteUser());

            model.addAttribute("username", request.getRemoteUser());
        }


        model.addAttribute("bodyContent", "login");
        return "master-template";
    }
}

