package com.example.university.controller;

import com.example.university.model.Course;
import com.example.university.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public String getAllCourses(Model model) {
        try {
            List<Course> courses = courseService.findAll();
            model.addAttribute("courses", courses);
            return "course";
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred while retrieving courses.");
            return "error";
        }
    }
}
