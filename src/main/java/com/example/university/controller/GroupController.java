package com.example.university.controller;

import com.example.university.model.Group;
import com.example.university.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GroupController {
    @Autowired
    private GroupService groupService;

    @GetMapping("/group")
    public String groupPage(Model model) {
        List<Group> groups = groupService.findAll();
        model.addAttribute("groups", groups);
        return "group";
    }
}
