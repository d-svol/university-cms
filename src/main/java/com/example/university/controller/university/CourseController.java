package com.example.university.controller.university;

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
        model.addAttribute("faculties", facultyService.getAllFaculties());
        model.addAttribute("courses", courseService.getAllCourses());
        return "university/course";
    }

    @PostMapping("/add")
    public String addCourse(@RequestParam String name,
                            @RequestParam String description,
                            @RequestParam Long facultyId,
                            RedirectAttributes redirectAttributes) {
        try {
            Faculty faculty = facultyService.getFacultyById(facultyId).orElseThrow(() ->
                    new IllegalArgumentException("Invalid faculty ID: " + facultyId));
            Course course = new Course();
            course.setName(name);
            course.setDescription(description);
            course.setFaculty(faculty);
            courseService.createCourse(course);
            redirectAttributes.addFlashAttribute("successMessage", "Course successfully added.");
        } catch (DataIntegrityViolationException e) {
            if (e.getMostSpecificCause().getMessage().contains("courses_name_key")) {
                redirectAttributes.addFlashAttribute("errorMessage", "Error: A course with this name already exists.");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Error adding course: " + e.getMostSpecificCause().getMessage());
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error adding course: " + e.getMessage());
        }
        return "redirect:/course";
    }

    @PostMapping("/delete")
    public String deleteCourse(@RequestParam Long courseId, RedirectAttributes redirectAttributes) {
        try {
            courseService.deleteCourse(courseId);
            redirectAttributes.addFlashAttribute("successMessage", "Course successfully deleted.");
        } catch (DataIntegrityViolationException e) {
            // Якщо виникає помилка через зовнішні ключі
            redirectAttributes.addFlashAttribute("errorMessage", "Cannot delete course: it is used in other tables.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting course: " + e.getMessage());
        }
        return "redirect:/course";
    }

    @PostMapping("/edit")
    public String editCourse(@RequestParam("currentName") String currentName,
                             @RequestParam("name") String name,
                             @RequestParam String description,
                             @RequestParam Long facultyId,
                             RedirectAttributes redirectAttributes) {
        try {
            Course courseOptional = courseService.getCourseByName(currentName).orElseThrow(() ->
                    new IllegalArgumentException("Invalid curse name: " + currentName));
            Optional<Faculty> faculty = facultyService.getFacultyById(facultyId);
            if (faculty.isPresent()) {
                courseOptional.setName(name);
                courseOptional.setDescription(description);
                courseOptional.setFaculty(faculty.get());
                courseService.updateCourse(courseOptional);
                redirectAttributes.addFlashAttribute("successMessage", "Course successfully updated.");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Faculty not found.");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error updating course: " + e.getMessage());
        }
        return "redirect:/course";
    }
}
