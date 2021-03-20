package wp.com.demo.web.controller;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wp.com.demo.model.Company;
import wp.com.demo.model.User;
import wp.com.demo.repository.CompanyRepository;
import wp.com.demo.service.CompanyService;
import wp.com.demo.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/company-profile")
public class CompanyProfileController {

    public static final String targetFolderImagePPPath = "C:\\Users\\PC\\Desktop\\Employee-managament-app\\WPproekt\\Employee-Management\\target\\classes\\static\\ProfilePictures";

    private final CompanyService companyService;
    private final CompanyRepository companyRepository;
    private final UserService userService;

    public CompanyProfileController(CompanyService companyService, CompanyRepository companyRepository, UserService userService) {
        this.companyService = companyService;
        this.companyRepository = companyRepository;
        this.userService = userService;
    }

    //TODO: redirect errori da sredam
//    @GetMapping("/{id}")
    @GetMapping("/{id}")
    public String getCompanyPage(@PathVariable Long id, HttpServletRequest request, Model model) {
        Company company = this.companyService.findById(id).get();

//        if (this.companyService.findById(id).isPresent()) {
//            Company company = this.companyService.findById(id).get();
//            String companyUser=company.getUser().getUsername();
//            String username=request.getRemoteUser();
//            model.addAttribute("companyUser",companyUser);
//            model.addAttribute("username",username);
//            model.addAttribute("company", company);
//            model.addAttribute("bodyContent", "companyProfile");
//            return "master-template";
//        }

        if (this.companyService.findById(id).isPresent()) {
            String username = request.getRemoteUser();
            if (this.companyService.getCompany(username).isPresent())
                model.addAttribute("company", company);
            model.addAttribute("bodyContent", "companyProfile");
            return "master-template";
        }
        else {
            model.addAttribute("company", company);
            model.addAttribute("bodyContent", "companyProfile");
        }
        return "redirect:/home";


//            if (company.getUser().getUsername().equals(request.getRemoteUser())) {
//                String username = request.getRemoteUser();
//                company = this.companyService.getCompany(username).orElseThrow(() -> new UsernameNotFoundException(username));
//                model.addAttribute("company", company);
//                model.addAttribute("bodyContent", "companyProfile");
//
//            }





    }


//


//
//        User user = this.userService.getUserInstanceByUUID(request.getRemoteUser());

//        model.addAttribute("username",user.getUsername());
//        model.addAttribute("companyId",((Company)user).getId());



//        }
//        return "redirect/:company?error=CompanyNotFound";


            //return "courses";


//    model.addAttribute("companyAds", this.adService.getAdsByCompanyId(((Company) user).getId()));
// za lsitanje na vraboteni spored kompanija}

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

        @PostMapping("/add")
        public String saveCompany (
                @RequestParam(required = false) Long id,
                @RequestParam String name,
                @RequestParam String description,
                @RequestParam String moto,
                @RequestParam String owner,
                @RequestParam Integer numEmployee,
                @RequestParam Integer numInterns,
                @RequestParam("image") MultipartFile profilePicture) throws IOException {
//
            if (!profilePicture.isEmpty()) {
                File picture_target = new File(profilePicture.getOriginalFilename());
//                    (targetFolderImagePPPath + request.getRemoteUser() + "." + profilePicture.getOriginalFilename().split("\\.")[1]);
                if (picture_target.exists()) {
                    picture_target.delete();

                }
                if (id != null) {
                    profilePicture.transferTo(picture_target);
                    this.companyService.edit(id, name, description, moto, owner, profilePicture, "../ProfilePictures/" + picture_target.getName(), numEmployee, numInterns);

//                return "redirect:/manage-companies";
                }
            } else {


                this.companyService.save(name, description, moto, owner, profilePicture, "../ProfilePictures/", numEmployee, numInterns);
            }
            return "redirect:/manage-companies";


        }
    }


