package com.example.university.controller.user;


import com.example.university.model.user.Role;
import com.example.university.model.user.UserEntity;
import com.example.university.service.user.RoleService;
import com.example.university.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

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
}