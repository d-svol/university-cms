package com.example.university.controller.user;

import com.example.university.model.user.Professor;
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

import java.util.List;


@WebMvcTest(ProfessorController.class)
class TestProfessorController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfessorService professorService;

    @Test
    @WithMockUser
    void testListFaculties() throws Exception {
        List<Professor> professors = List.of(
                new Professor(1L, 2L),
                new Professor(2L, 2L)
        );
        Mockito.when(professorService.getAllProfessors()).thenReturn(professors);

        mockMvc.perform(MockMvcRequestBuilders.get("/professors"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("/users/professors"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("professors"));

    }
}
