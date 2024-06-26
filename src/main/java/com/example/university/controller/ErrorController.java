package com.example.university.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/error")
    public String showErrorPage(Model model, HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (statusCode != null && !model.containsAttribute("statusCode")) {
            model.addAttribute("statusCode", statusCode);
        }

        String errorMessage = (String) model.asMap().get("errorMessage");
        if (errorMessage == null) {
            errorMessage = "An unexpected error has occurred.";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }
}


