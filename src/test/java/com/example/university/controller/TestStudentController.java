package com.example.university.controller;

import com.example.university.model.Student;
import com.example.university.service.StudentService;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
class TestStudentController {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private StudentService studentService;

    @Test
    @WithMockUser
    void getAllStudents() throws Exception {
        List<Student> mockStudents = Arrays.asList(
                new Student(1L, "Name1", "Surname1"),
                new Student(2L, "Name2", "Surname2")
        );

        when(studentService.findAll()).thenReturn(mockStudents);

        mvc.perform(get("/student"))
                .andExpect(status().isOk())
                .andExpect(view().name("student"))
                .andExpect(model().attributeExists("students"))
                .andExpect(model().attribute("students", mockStudents));
    }
}
