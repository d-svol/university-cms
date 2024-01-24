package com.example.university.controller.university;

import com.example.university.controller.university.GroupController;
import com.example.university.service.university.GroupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TestGroupController {
    @Mock
    private GroupService groupService;

    @InjectMocks
    private GroupController groupController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(groupController).build();
    }

    @Test
    public void testListGroups() throws Exception {
        given(groupService.getAllGroups()).willReturn(Collections.emptyList());

        mockMvc.perform(get("/group"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("groups"))
                .andExpect(view().name("university/group"));
    }
}
