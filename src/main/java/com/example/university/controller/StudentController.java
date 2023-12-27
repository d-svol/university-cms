package com.example.university.controller;

import com.example.university.model.Student;
import com.example.university.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String getAllStudents(Model model) {
        try {
            List<Student> students = studentService.findAll();
            model.addAttribute("students", students);
            return "student";
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred while retrieving students.");
            return "error";
        }
    }
}
