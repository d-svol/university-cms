package com.example.university.controller.university;

import com.example.university.model.university.Course;
import com.example.university.model.university.Faculty;
import com.example.university.service.university.CourseService;
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

import java.util.List;

@WebMvcTest(CourseController.class)
public class TestCourseController {
    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private CourseService courseService;

    @MockBean
    private FacultyService facultyService;

    @Test
    @WithMockUser
    void coursesPage_ShouldReturnCourses() throws Exception {
        List<Faculty> faculties = List.of(new Faculty(1L, "Faculty 1"));
        List<Course> courses = List.of(new Course(1L, "Course 1", "Description 1", faculties.getFirst()));

        Mockito.when(facultyService.getAllFaculties()).thenReturn(faculties);
        Mockito.when(courseService.getAllCourses()).thenReturn(courses);

        mockMvc.perform(MockMvcRequestBuilders.get("/course"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("university/course"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("faculties"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("courses"));
    }
}
