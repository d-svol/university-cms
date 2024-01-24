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

    @GetMapping("/assignRole")
    public String assignRolePage() {
        return "assignRole";
    }

    @PostMapping("/assignRole")
    public String assignRole(@RequestParam("username") String username, @RequestParam("role") String roleName) {
        Optional<UserEntity> userOpt = userService.getByUsername(username);

        if (userOpt.isPresent()) {
            UserEntity user = userOpt.get();
            Optional<Role> roleOpt = roleService.getRoleByName(roleName);

            if (roleOpt.isPresent()) {
                Role role = roleOpt.get();
                user.setRole(role);
                userService.saveUser(user);
                return "redirect:/success";
            }
        }

        return "redirect:/error";
    }

}