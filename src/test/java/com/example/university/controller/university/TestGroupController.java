package com.example.university.controller.university;

import com.example.university.dto.GroupDTO;
import com.example.university.service.university.CourseService;
import com.example.university.service.university.GroupService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@WebMvcTest(GroupController.class)
class TestGroupController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GroupService groupService;

    @MockBean
    private CourseService courseService;

    @Test
    @WithMockUser
    void listGroups_ShouldReturnGroups() throws Exception {
        List<GroupDTO> groupDTOList = List.of(
                new GroupDTO(1L, "Group 1", "Course 1"),
                new GroupDTO(2L, "Group 2", "Course 2")
        );
        Mockito.when(groupService.getAllGroupsDTOs()).thenReturn(groupDTOList);

        mockMvc.perform(MockMvcRequestBuilders.get("/groups"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("university/groups"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("groups"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("courses"));
    }
}
