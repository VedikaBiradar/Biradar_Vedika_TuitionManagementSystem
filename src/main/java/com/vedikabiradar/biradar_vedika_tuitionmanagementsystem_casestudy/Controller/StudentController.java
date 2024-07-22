package com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Controller;


import com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Entity.Student;
import com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Entity.User;
import com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Service.StudentService;
import com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserService userService;


    //get mapping method to get studentRegistration webpage
    @GetMapping("/studentRegister")
    public String showAddStudentForm(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("student", new Student());
        model.addAttribute("parentId"); // Replace with actual parentId
//        model.addAttribute("parents", parents);
        return "studentRegistration";
    }

    //post mapping method to save student Information
    @PostMapping("/studentRegister")
    public String saveStudent(@ModelAttribute("student") Student student,
                              @RequestParam("parentId") Long parentId) {
        Optional<User> user= userService.getUserById(parentId);
        student.setUser(user.get());
        studentService.saveStudent(student);
        return "redirect:/studentRegister";
    }


    //get mapping method to get all student list
    @GetMapping("/registeredStudent/list")
    public String listStudent(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "registeredStudents";
    }

    //get mapping method to get deleteStudent webpage
    @GetMapping("/deleteStudent")
    public String showDeleteStudentPage() {
        return "deleteStudent";
    }


    //post mapping method to delete student Information
    @PostMapping("/deleteStudent")
    public String deleteStudent(@RequestParam("studentId") Long studentId, Model model) {
        if (studentService.existsById(studentId)) {
            studentService.deleteById(studentId);
            model.addAttribute("successMessage", "Student deleted successfully.");
        } else {
            model.addAttribute("errorMessage", "Student ID is not valid.");
        }
        return "deleteStudent";
    }

}
