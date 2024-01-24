package com.example.university.controller.university;

import com.example.university.model.university.Faculty;
import com.example.university.service.university.FacultyService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(FacultyController.class)
@WithMockUser(authorities = "ADMIN")
class TestFacultyController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyService facultyService;

    @Test
    public void listFaculties_ShouldReturnFaculties() throws Exception {
        List<Faculty> faculties = Arrays.asList(
                new Faculty(1, "Faculty 1"),
                new Faculty(2, "Faculty 2")
        );
        Mockito.when(facultyService.getAllFaculties()).thenReturn(faculties);

        mockMvc.perform(MockMvcRequestBuilders.get("/faculty"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("university/faculty"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("faculties"));
    }
}