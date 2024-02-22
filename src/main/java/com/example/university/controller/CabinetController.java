package com.example.university.controller;


import com.example.university.dto.StudentDTO;
import com.example.university.model.university.Faculty;
import com.example.university.model.university.Group;
import com.example.university.model.user.Role;
import com.example.university.model.user.UserEntity;
import com.example.university.service.university.FacultyService;
import com.example.university.service.university.GroupService;
import com.example.university.service.user.RoleService;
import com.example.university.service.user.StudentService;
import com.example.university.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CabinetController {
    private final UserService userService;
    private final RoleService roleService;
    private final FacultyService facultyService;
    private final GroupService groupService;
    private final StudentService studentService;

    @GetMapping("/anonymouscab")
    public String anonymousCabinetPage(Model model) {
        return "anonymouscab";
    }

    @GetMapping("/adminscab")
    public String adminCabinetPage(Model model) {
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("roles", roles);
        return "adminscab";
    }

    @GetMapping("/professorscab")
    public String professorCabinetPage(Authentication authentication, Model model) {
        Optional<UserEntity> professor = userService.findProfessorByUsername(authentication.getName());
        List<Faculty> faculties = facultyService.getAllFaculties();
        model.addAttribute("professor", professor.get());
        model.addAttribute("availableFaculties", faculties);
        return "professorscab";
    }


    @GetMapping("/studentscab")
    public String studentCabinetPage(Authentication authentication, Model model) {
        List<Group> groups = groupService.getAllGroups();
        StudentDTO studentDTO = studentService.getStudentDTOByName(authentication.getName());
        model.addAttribute("groupName", studentDTO.getGroupName());
        model.addAttribute("username", studentDTO.getStudentName());

        return "studentscab";
    }

    @GetMapping("/stuffscab")
    public String stuffCabinetPage(Model model) {
        return "stuffscab";
    }
}
