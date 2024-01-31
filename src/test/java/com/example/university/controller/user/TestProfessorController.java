package com.example.university.controller.user;

import com.example.university.dto.ProfessorDTO;
import com.example.university.model.user.Professor;
import com.example.university.service.university.CourseService;
import com.example.university.service.user.ProfessorService;
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


@WebMvcTest(ProfessorController.class)
class TestProfessorController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfessorService professorService;

    @MockBean
    private CourseService courseService;

    @Test
    @WithMockUser
    void professorPage() throws Exception {
        List<ProfessorDTO> mockProfessors = Arrays.asList(
                new ProfessorDTO(1L, "Professor 1", "Course 1"),
                new ProfessorDTO(2L, "Professor 2", "Course 2")
        );

        Mockito.when(professorService.getAllProfessorDTOs()).thenReturn(mockProfessors);

        mockMvc.perform(MockMvcRequestBuilders.get("/professors"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("professors"))
                .andExpect(MockMvcResultMatchers.view().name("/users/professors"));
    }
}
