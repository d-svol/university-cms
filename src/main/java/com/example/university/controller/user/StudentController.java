package com.example.university.controller.user;

import com.example.university.dto.StudentDTO;
import com.example.university.service.university.GroupService;
import com.example.university.service.user.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    private final GroupService groupService;

    @GetMapping
    public String studentPage(Model model) {
        StudentDTO studentDTO = new StudentDTO();
        model.addAttribute("studentDTO", studentDTO);
        model.addAttribute("groups", groupService.getAllGroups());
        model.addAttribute("students", studentService.getAllStudents());
        return "/users/student";
    }

    @PostMapping("edit")
    public String editStudent(@ModelAttribute StudentDTO studentDTO, RedirectAttributes redirectAttributes) {
        studentService.updateStudentGroup(studentDTO);
        redirectAttributes.addFlashAttribute("successMessage", "User successfully added.");
        return "redirect:/student";
    }
}
