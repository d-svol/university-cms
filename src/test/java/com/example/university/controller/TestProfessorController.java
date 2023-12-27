package com.example.university.controller;

import com.example.university.model.Professor;
import com.example.university.service.ProfessorService;
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
@WebMvcTest(ProfessorController.class)
class TestProfessorController {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProfessorService professorService;

    @Test
    @WithMockUser
    void getAllProfessors() throws Exception {
        List<Professor> mockProfessors = Arrays.asList(
                new Professor(1L, "Name1", "Surname1"),
                new Professor(2L, "Name2", "Surname2")
        );

        when(professorService.findAll()).thenReturn(mockProfessors);

        mvc.perform(get("/professor"))
                .andExpect(status().isOk())
                .andExpect(view().name("professor"))
                .andExpect(model().attributeExists("professors"))
                .andExpect(model().attribute("professors", mockProfessors));

        verify(professorService, times(1)).findAll();
        verifyNoMoreInteractions(professorService);
    }
}
