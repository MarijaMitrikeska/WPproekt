package wp.com.demo.web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wp.com.demo.model.Company;
import wp.com.demo.model.Employee;
import wp.com.demo.model.User;
import wp.com.demo.repository.CompanyRepository;
import wp.com.demo.service.CompanyService;
import wp.com.demo.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/company-profile")
public class CompanyProfileController {

    public static final String targetFolderImagePPPath = "C:\\Users\\PC\\Desktop\\Employee-managament-app\\WPproekt\\Employee-Management\\target\\classes\\static\\ProfilePictures";

    private final CompanyService companyService;


    public CompanyProfileController(CompanyService companyService) {
        this.companyService = companyService;

    }



    @GetMapping("/{id}")
    public String getCompanyPage(@PathVariable Long id, HttpServletRequest request, Model model) {
        if (this.companyService.findById(id).isPresent()) {
            Company company = this.companyService.findById(id).get();

            model.addAttribute("username", request.getRemoteUser());

            model.addAttribute("company", company);

                model.addAttribute("bodyContent", "companyProfile");
                return "master-template";
        }
        return "redirect/:company?error=CompanyNotFound";
    }




        @GetMapping("/edit/{id}")
        public String editCompanyProfilePage (@PathVariable Long id, Model model){
            if (this.companyService.findById(id).isPresent()) {
                Company company = this.companyService.findById(id).get();
                model.addAttribute("company", company);
                model.addAttribute("bodyContent", "edit-company");
                return "master-template";
            }
            return "redirect/:company?error=CompanyNotFound";
        }

        @PostMapping("/add/{id}")
        public String saveEditedCompany (
                HttpServletRequest request,
                @PathVariable Long id,
                @RequestParam String name,
                @RequestParam String description,
                @RequestParam String moto,
                @RequestParam String owner,
                @RequestParam Integer numEmployee,
                @RequestParam Integer numInterns,
                @RequestParam("image") MultipartFile profilePicture) throws IOException {

            String user=request.getRemoteUser();

            if (!profilePicture.isEmpty()) {
                File picture_target = new File(profilePicture.getOriginalFilename());
//                   (targetFolderImagePPPath + request.getRemoteUser() + "." + profilePicture.getOriginalFilename().split("\\.")[1]);
                if (picture_target.exists()) {
                    picture_target.delete();

                }

                    profilePicture.transferTo(picture_target);
                    this.companyService.edit(user,id, name, description, moto, owner, profilePicture, "../ProfilePictures/" + picture_target.getName(), numEmployee, numInterns);

              return "redirect:/company-profile/{id}";

            }
            this.companyService.edit(user,id, name, description, moto, owner, profilePicture, "../ProfilePictures/" , numEmployee, numInterns);

            return "redirect:/company-profile/{id}";


        }
    }


