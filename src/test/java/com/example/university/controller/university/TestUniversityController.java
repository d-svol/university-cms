package com.example.university.controller.university;

import com.example.university.model.university.University;
import com.example.university.service.university.FacultyService;
import com.example.university.service.university.UniversityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.BDDMockito.given;

@WebMvcTest(UniversityController.class)
@WithMockUser(authorities = "ADMIN")
public class TestUniversityController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UniversityService universityService;

    @MockBean
    private FacultyService facultyService;

    @Test
    public void listUniversities_ShouldReturnUniversities() throws Exception {
        List<University> universities = List.of(
                new University(1L, "University 1"),
                new University(2L, "University 2")
        );

        given(universityService.getAllUniversities()).willReturn(universities);

        mockMvc.perform(MockMvcRequestBuilders.get("/university"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("university/university"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("university"));
    }
}
