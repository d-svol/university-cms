package com.example.university.customexception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", "Error: " + e.getMessage());
        return "redirect:/error";
    }

    @ExceptionHandler(UniversityNotFoundException.class)
    public String handleUniversityNotFoundException(UniversityNotFoundException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", "University not found: " + ex.getMessage());
        return "redirect:/error";
    }

    @ExceptionHandler(GroupNotFoundException.class)
    public String handleGroupNotFoundException(GroupNotFoundException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", "Group not found: " + ex.getMessage());
        return "redirect:/error";
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDataIntegrityViolationException(DataIntegrityViolationException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", "Data integrity violation: " + ex.getMostSpecificCause().getMessage());
        return "redirect:/error";
    }
}

