package com.example.university.controller;

import com.example.university.model.Student;
import com.example.university.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class StudentController {
    @Autowired
    private StudentServiceImpl studentService;

    @GetMapping("/student")
    public String professorsPage(Model model) {
        List<Student> students = studentService.findAll();
        model.addAttribute("students", students);
        return "student";
    }
}
