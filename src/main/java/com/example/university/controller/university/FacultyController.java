package com.example.university.controller.university;

import com.example.university.model.university.Faculty;
import com.example.university.service.university.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/faculties")
public class FacultyController {
    private final FacultyService facultyService;

    @Autowired
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping
    public String listFaculties(Model model) {
        model.addAttribute("faculty", new Faculty());
        model.addAttribute("faculties", facultyService.getAllFaculties());
        return "university/faculties";
    }

    @PostMapping("/add")
    public String addFaculties(@ModelAttribute("faculty") Faculty faculty,
                               RedirectAttributes redirectAttributes) {
        facultyService.addFaculty(faculty);
        redirectAttributes.addFlashAttribute("successMessage", "Faculty add successfully.");
        return "redirect:/faculties";
    }

    @PostMapping("/edit")
    public String editFaculty(@ModelAttribute("faculty") Faculty faculty, RedirectAttributes redirectAttributes) {
        facultyService.updateFaculty(faculty);
        redirectAttributes.addFlashAttribute("successMessage", "Faculty edit successfully.");
        return "redirect:/faculties";
    }

    @PostMapping("/delete")
    public String deleteFaculty(@RequestParam("facultyId") Long facultyId,
                                RedirectAttributes redirectAttributes) {
        facultyService.deleteFacultyById(facultyId);
        redirectAttributes.addFlashAttribute("successMessage", "Faculty deleted successfully.");
        return "redirect:/faculties";
    }
}
