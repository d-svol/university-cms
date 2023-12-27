package com.example.university.controller;

import com.example.university.model.Group;
import com.example.university.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/group")
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public String getAllGroups(Model model) {
        try {
            List<Group> groups = groupService.findAll();
            model.addAttribute("groups", groups);
            return "group";
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred while retrieving groups.");
            return "error";
        }
    }
}
