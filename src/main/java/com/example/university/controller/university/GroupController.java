package com.example.university.controller.university;

import com.example.university.service.university.CourseService;
import com.example.university.service.university.GroupService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/groups")
public class GroupController {
    private final GroupService groupService;
    private final CourseService courseService;

    public GroupController(GroupService groupService, CourseService courseService) {
        this.groupService = groupService;
        this.courseService = courseService;
    }

    @GetMapping
    public String listGroups(Model model) {
        model.addAttribute("groups", groupService.getAllGroupsDTOs());
        model.addAttribute("courses", courseService.getAllCoursesDTO());
        return "university/groups";
    }

    @PostMapping("/add")
    public String addGroup(@RequestParam String groupName, @RequestParam Long courseId, RedirectAttributes redirectAttributes) {
        groupService.addGroup(groupName, courseId);
        redirectAttributes.addFlashAttribute("successMessage", "Group successfully added!");
        return "redirect:/groups";
    }

    @PostMapping("/edit")
    public String editGroup(@RequestParam Long groupId,
                            @RequestParam String groupName,
                            @RequestParam Long courseId,
                            RedirectAttributes redirectAttributes) {
        groupService.updateGroup(groupId, groupName, courseId);
        redirectAttributes.addFlashAttribute("successMessage", "Group successfully updated .");
        return "redirect:/groups";
    }

    @PostMapping("/delete")
    public String deleteGroup(@RequestParam("groupId") Long groupId, RedirectAttributes redirectAttributes) {
        groupService.deleteGroup(groupId);
        redirectAttributes.addFlashAttribute("successMessage", "Group successfully deleted.");
        return "redirect:/groups";
    }
}
