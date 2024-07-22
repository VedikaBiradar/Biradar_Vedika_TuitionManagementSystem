package com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Controller;


import com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Entity.ContactMessage;
import com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Service.ContactMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactController {

    private final ContactMessageService contactMessageService;

    @Autowired
    public ContactController(ContactMessageService contactMessageService) {
        this.contactMessageService = contactMessageService;
    }

    //get mapping to get contactus page
    @GetMapping("/contact")
    public String showContactForm(Model model) {
        model.addAttribute("contactMessage", new ContactMessage());
        return "contact";
    }


    //post mapping to save data in database
    @PostMapping("/contact")
    public String submitContactForm(@ModelAttribute ContactMessage contactMessage) {
        contactMessageService.saveMessage(contactMessage);
        return "redirect:/contact?success";
    }



    //get mapping to see contact list
    @GetMapping("/contact/list")
    public String showListOfContactMessages(Model model) {
        model.addAttribute("contactMessages", contactMessageService.getAllMessages());
        return "displayAllContactUS";
    }

    //get mapping to get index page
    @GetMapping("/index")
    public String showIndexForm(Model model) {
        return "index";
    }

    //get mapping method to get about us page
    @GetMapping("/about")
    public String showAboutus(Model model) {
        return "about";
    }


    //get mapping method to get eligibility check and calculate fee page
    @GetMapping("/calculate")
    public String showCalculateFeeForm(Model model) {
        return "calculate fee";
    }



}


