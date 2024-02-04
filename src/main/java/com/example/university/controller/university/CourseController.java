package com.example.university.controller.university;

import com.example.university.dto.CourseDTO;
import com.example.university.model.university.Course;
import com.example.university.model.university.Faculty;
import com.example.university.model.user.Role;
import com.example.university.service.university.CourseService;
import com.example.university.service.university.FacultyService;
import com.example.university.service.user.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;
    private final FacultyService facultyService;

    @Autowired
    public CourseController(CourseService courseService, FacultyService facultyService) {
        this.courseService = courseService;
        this.facultyService = facultyService;
    }

    @GetMapping
    public String coursesPage(Authentication authentication, Model model) {
        model.addAttribute("courseDTO", new CourseDTO());
        model.addAttribute("faculties", facultyService.getAllFaculties());
        model.addAttribute("courses", courseService.getAllCourses());
        return "university/course";
    }

    @PostMapping("/add")
    public String addCourse(@ModelAttribute CourseDTO courseDTO, RedirectAttributes redirectAttributes) {
        courseService.createCourse(courseDTO);
        redirectAttributes.addFlashAttribute("successMessage", "Course successfully added.");
        return "redirect:/course";
    }

    @PostMapping("/delete")
    public String deleteCourse(@RequestParam Long courseId, RedirectAttributes redirectAttributes) {
        courseService.deleteCourse(courseId);
        redirectAttributes.addFlashAttribute("successMessage", "Course successfully deleted.");
        return "redirect:/course";
    }

    @PostMapping("/edit")
    public String editCourse(@ModelAttribute CourseDTO courseDTO, RedirectAttributes redirectAttributes) {
        courseService.updateCourse(courseDTO);
        redirectAttributes.addFlashAttribute("successMessage", "Course successfully updated.");
        return "redirect:/course";
    }
}
