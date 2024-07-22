package com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Controller;


import com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Entity.StandardSubject;
import com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Service.StandardSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StandardSubjectController {

    private StandardSubjectService standardSubjectService;

    @Autowired
    public StandardSubjectController(StandardSubjectService standardSubjectService) {
        this.standardSubjectService = standardSubjectService;
    }

    //get mapping method to get standardSubject webpage
    @GetMapping("/standardSubject")
    public String showStandardSubjectForm(Model model) {
        model.addAttribute("standardSubject", new StandardSubject());
        return "standardSubject";
    }


    //Post mapping method to save standardSubject
    @PostMapping("/standardSubject")
    public String submitStandardSubjectForm(@ModelAttribute StandardSubject standardSubject) {
        standardSubjectService.saveStandardSubject(standardSubject);
        return "redirect:/standardSubject?success";
    }


    //get mapping method to get standardSubject list
    @GetMapping("/standardSubject/list")
    public String showListOfStandardSubject(Model model) {
        model.addAttribute("standardSubjects", standardSubjectService.getAllStandardSubjects());
        return "displayAllStandardSubjects";
    }



}

