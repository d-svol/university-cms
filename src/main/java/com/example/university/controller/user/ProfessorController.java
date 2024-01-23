package com.example.university.controller.user;

import com.example.university.service.user.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
@RequestMapping("/professors")
public class ProfessorController {

    private final ProfessorService professorService;

    @GetMapping
    public String professorPage(Model model) {
        model.addAttribute("professors", professorService.getAllProfessors());
        return "/users/professors";
    }
}
