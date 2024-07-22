package com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Controller;

import com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Dto.UserDto;
import com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Entity.Student;
import com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Entity.User;
import com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Service.StudentService;
import com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class AuthorizeController {

    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    public AuthorizeController(UserService userService) {
        this.userService = userService;
    }

    //get Mapping to get login form
    @GetMapping("/login")
    public String loginForm()
    {
        return "login";
    }


    //get mapping to get userRegistration form
    @GetMapping("/userRegistration")
    public String userRegistration(Model model){
        UserDto userDto =new UserDto();
        model.addAttribute("user", userDto);
        return "userRegistration";
    }


    //post mapping to save registration data
    @PostMapping("/userRegistration/save")
    public String saveRegistration(@Valid @ModelAttribute("user") UserDto user, BindingResult result, Model model) {
        User existing =userService.findUserByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "userRegistration";
        }

        userService.saveUser(user);
        return "redirect:/userRegistration?success";
    }



    // get mapping to get registeredUsers List
    @GetMapping("/users")
    public String listRegisteredUsers(Model model){
        List<UserDto> users= userService.findAllUsers();
        model.addAttribute("users", users);
        return "registeredUsers";

    }

    //get mapping to get adminDashboard
    @GetMapping("/adminDashboard")
    public String adminDashboard() {
        return "adminDashboard";  // Ensure you have this template
    }


    //get mapping for parentDashboard
    @GetMapping("/parentDashboard")
    public String userDashboard() {
        return "parentDashboard";  // Ensure you have this template
    }


    //get mapping to get studentInfo page
    @GetMapping("/studentInfo")
    public String showParentInfoForm(Model model) {
        model.addAttribute("email", "");
        return "studentInfo";
    }


    //post mapping to check Student Information present or not
    @PostMapping("/studentInfo")
    public String getParentInfo(@RequestParam("email") String email, Model model) {
        User user = userService.findByEmail(email);
        if (user != null) {
            List<Student> students = studentService.findByUser(user);
            model.addAttribute("student", students);
        } else {
            model.addAttribute("error", "No parent found with the provided email.");
        }
        model.addAttribute("email", email);
        return "studentInfo";
    }

    //Get mapping method to get DeleteParent webPage
    @GetMapping("/deleteParent")
    public String showDeleteParentPage() {
        return "deleteParent";
    }



    //post mapping to see parent deleted successfully or not
    @PostMapping("/deleteParent")
    public String deleteParent(@RequestParam("email") String email, Model model) {
        if (email.endsWith("@admin.com")) {
            model.addAttribute("errorMessage", "This is an admin email, not able to delete.");
        } else if (!userService.deleteParentByEmail(email)) {
            model.addAttribute("errorMessage", "Invalid email, not present.");
        } else {
            model.addAttribute("successMessage", "Parent deleted successfully.");
        }
        return "deleteParent";
    }
}





