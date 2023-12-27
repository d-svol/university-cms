package com.example.university.controller;

import com.example.university.model.Professor;
import com.example.university.service.ProfessorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/professor")
public class ProfessorController {
    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping
    public String getAllProfessors(Model model) {
        try {
            List<Professor> professors = professorService.findAll();
            model.addAttribute("professors", professors);
            return "professor";
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred while retrieving professors.");
            return "error";
        }
    }
}
