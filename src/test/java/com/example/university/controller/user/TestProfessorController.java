package com.example.university.controller.user;

import com.example.university.dto.ProfessorDTO;
import com.example.university.model.university.Course;
import com.example.university.service.university.CourseService;
import com.example.university.service.user.ProfessorService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
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
                new ProfessorDTO(1L, "Professor 1", List.of("Course 1", "Course 2")),
                new ProfessorDTO(2L, "Professor 2", List.of("Course 3"))
        );

        Mockito.when(professorService.getAllProfessorDTOs()).thenReturn(mockProfessors);

        mockMvc.perform(MockMvcRequestBuilders.get("/professors"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("professors"))
                .andExpect(MockMvcResultMatchers.view().name("/users/professors"));
    }

    @Test
    @WithMockUser(username = "professor", roles = {"PROFESSOR"})
    void listProfessorCourses() throws Exception {
        List<Course> courses = Arrays.asList(
                new Course(1L, "Course Name 1", "Description 1"),
                new Course(2L, "Course Name 2", "Description 2"));

        Mockito.when(courseService.findAllCoursesByProfessorUsername(ArgumentMatchers.anyString())).thenReturn(courses);

        mockMvc.perform(MockMvcRequestBuilders.get("/professors/courses"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("professors/courses"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("courses"))
                .andExpect(MockMvcResultMatchers.model().attribute("courses", courses));
    }
}
