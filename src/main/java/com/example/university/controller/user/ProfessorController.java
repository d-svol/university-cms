package com.example.university.controller.user;

import com.example.university.dto.ProfessorDTO;
import com.example.university.service.university.CourseService;
import com.example.university.service.user.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/professors")
public class ProfessorController {
    private final ProfessorService professorService;
    private final CourseService courseService;

    @GetMapping
    public String professorPage(Model model) {
        List<ProfessorDTO> professorDTOs = professorService.getAllProfessors();
        model.addAttribute("professors", professorDTOs);
        model.addAttribute("courses", courseService.getAllCoursesDTO());
        return "/users/professors";
    }

    @PostMapping("/edit")
    public String editProfessor(@RequestParam Long professorId,
                                @RequestParam(required = false) Long courseId,
                                RedirectAttributes redirectAttributes) {
        professorService.editProfessor(professorId, courseId);
        redirectAttributes.addFlashAttribute("successMessage", "Professor updated successfully");

        return "redirect:/professors";
    }
}
