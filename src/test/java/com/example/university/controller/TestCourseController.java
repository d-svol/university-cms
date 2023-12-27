package com.example.university.controller;

import com.example.university.model.Course;
import com.example.university.service.CourseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CourseController.class)
class TestCourseController {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CourseService service;

    @Test
    @WithMockUser
    void getAllCourses() throws Exception {
        List<Course> mockCourses = Arrays.asList(
                new Course(1L, "Course1"),
                new Course(2L, "Course2")
        );

        when(service.findAll()).thenReturn(mockCourses);

        mvc.perform(get("/course"))
                .andExpect(status().isOk())
                .andExpect(view().name("course"))
                .andExpect(model().attributeExists("courses"))
                .andExpect(model().attribute("courses", mockCourses));

        verify(service, times(1)).findAll();
        verifyNoMoreInteractions(service);
    }
}
