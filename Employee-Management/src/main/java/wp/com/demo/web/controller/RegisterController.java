package wp.com.demo.web.controller;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wp.com.demo.model.enums.Role;
import wp.com.demo.model.exceptions.InvalidCredentialsException;
import wp.com.demo.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error,
                                  @RequestParam(required = false) String success,
                                   Model model) {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("errorMessage", error);
        }
       if(success != null && !success.isEmpty()) {
            model.addAttribute("Success", true);
            model.addAttribute("successMessage", success);
        }
        model.addAttribute("bodyContent", "register");
        return "master-template";
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String email,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam Role role) {
        try{
            this.userService.register(username,email, password, repeatedPassword,role);
            return "redirect:/login?success=AccountSuccessfullyCreated";
            //return "redirect:/login";
        }
        catch (Exception exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }

    }

}
