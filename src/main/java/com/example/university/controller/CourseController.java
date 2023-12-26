package com.example.university.controller;


import com.example.university.model.Course;
import com.example.university.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/course")
    public String coursesPage(Model model) {
        List<Course> courses = courseService.findAll();
        model.addAttribute("courses", courses);
        return "course";
    }
}
