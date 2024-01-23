package com.example.university.controller.user;

import com.example.university.service.user.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public String studentPage(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "/users/student";
    }
}
