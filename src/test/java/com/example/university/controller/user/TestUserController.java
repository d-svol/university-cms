package com.example.university.controller.user;

import com.example.university.model.user.Role;
import com.example.university.model.user.UserEntity;
import com.example.university.service.user.RoleService;
import com.example.university.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;

@WebMvcTest(UserController.class)
@WithMockUser(authorities = "ADMIN")
public class TestUserController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private RoleService roleService;


    @Test
    public void userPage_ShouldReturnUsersAndRoles() throws Exception {
        List<UserEntity> users = Collections.singletonList(
                new UserEntity(1L, "Name", "1234",
                        new Role(1L, "ADMIN")));
        List<Role> roles = Collections.singletonList(new Role());

        Mockito.when(userService.getAllUsers()).thenReturn(users);
        Mockito.when(roleService.getAllRoles()).thenReturn(roles);

        mockMvc.perform(MockMvcRequestBuilders.get("/user"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("users/user"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("users", "roles"));
    }
}
