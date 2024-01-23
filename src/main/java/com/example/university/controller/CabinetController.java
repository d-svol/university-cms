package com.example.university.controller;


import com.example.university.model.StudentCabinetData;
import com.example.university.model.university.Faculty;
import com.example.university.model.university.Group;
import com.example.university.model.user.UserEntity;
import com.example.university.service.UserCabinetService;
import com.example.university.service.university.FacultyService;
import com.example.university.service.university.GroupService;
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
    private final StudentService studentService;
    private final FacultyService facultyService;
    private final GroupService groupService;
    private final UserCabinetService userCabinetService;

    @GetMapping("/adminscab")
    public String adminCabinetPage() {
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
        List<Faculty> faculties = facultyService.getAllFaculties();
        StudentCabinetData cabinetData = userCabinetService.getStudentCabinetData(authentication.getName());

//        model.addAttribute("userId", cabinetData.getUserID());
//        model.addAttribute("username", cabinetData.getUsername());
//        model.addAttribute("studentId", cabinetData.getStudentId());
//        model.addAttribute("studentGroup", cabinetData.getStudentGroup());
//        model.addAttribute("availableGroups", cabinetData.getAvailableGroups());
        model.addAttribute("groupId", cabinetData.getGroupId());
        model.addAttribute("groupName", cabinetData.getGroupName());
        model.addAttribute("availableGroups", groups);
        model.addAttribute("availableFaculties", faculties);

        return "studentscab";
    }
}
