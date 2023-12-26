package com.example.university.controller;


import com.example.university.model.Professor;
import com.example.university.service.impl.ProfessorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProfessorController {
    @Autowired
    private ProfessorServiceImpl professorService;

    @GetMapping("/professor")
    public String professorsPage(Model model) {
        List<Professor> professors = professorService.findAll();
        model.addAttribute("professors", professors);
        return "professor";
    }
}
