package com.example.university.controller;

import com.example.university.model.Group;
import com.example.university.service.GroupService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(GroupController.class)
class TestGroupController {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GroupService groupService;

    @Test
    @WithMockUser
    void testGetAllGroups() throws Exception {
        List<Group> mockGroups = Arrays.asList(
                new Group(1L, "Group1"),
                new Group(2L, "Group2")
        );

        when(groupService.findAll()).thenReturn(mockGroups);

        mvc.perform(get("/group"))
                .andExpect(status().isOk())
                .andExpect(view().name("group"))
                .andExpect(model().attributeExists("groups"))
                .andExpect(model().attribute("groups", mockGroups));

        verify(groupService, times(1)).findAll();
        verifyNoMoreInteractions(groupService);
    }
}
