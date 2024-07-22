package com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Controller;



import com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Entity.User;
import com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShowUserInfoController {

    @Autowired
    private UserService userService; // Assuming you have a UserService to fetch user details

    //get mapping method to get all user information
    @GetMapping("/userinfo")
    public String showUserInfoForm(Model model) {
        model.addAttribute("email", new String());
        return "userInfo";
    }

    //getting user information based on their email
    @PostMapping("/userinfo")
    public String getUserInfo(@RequestParam("email") String email, Model model) {
        User user = userService.findByEmail(email);
        if (user != null) {
            model.addAttribute("user", user);
            return "userInfo";
        } else {
            model.addAttribute("error", "User not found");
            return "userInfo";
        }
    }
}