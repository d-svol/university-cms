package com.example.university.controller.user;


import com.example.university.dto.UserDTO;
import com.example.university.model.user.Role;
import com.example.university.model.user.UserEntity;
import com.example.university.service.user.RoleService;
import com.example.university.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    @GetMapping
    public String userPage(Model model) {
        List<UserEntity> users = userService.getAllUsers();
        List<Role> roles = roleService.getAllRoles();

        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        return "users/user";
    }

    @PostMapping("add")
    public String addUser(@ModelAttribute UserDTO userDTO, RedirectAttributes redirectAttributes) {
        userService.addUser(userDTO);
        redirectAttributes.addFlashAttribute("successMessage", "User successfully added.");
        return "redirect:/user";
    }

    @PostMapping("edit")
    public String editUser(@ModelAttribute UserDTO userDTO, RedirectAttributes redirectAttributes) {
        userService.updateUser(userDTO);
        redirectAttributes.addFlashAttribute("successMessage", "User successfully added.");
        return "redirect:/user";
    }

    @PostMapping("delete")
    public String deleteUser(@RequestParam Long userId, RedirectAttributes redirectAttributes) {
        userService.deleteUserById(userId);
        redirectAttributes.addFlashAttribute("successMessage", "User successfully added.");
        return "redirect:/user";
    }
}