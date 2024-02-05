package com.example.university.controller.university;

import com.example.university.dto.CourseDTO;
import com.example.university.model.university.Faculty;
import com.example.university.service.university.CourseService;
import com.example.university.service.university.FacultyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


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
        List<CourseDTO> courses = List.of(new CourseDTO(1L, "Course 1", "Description 1",
                faculties.getFirst().getId(),
                faculties.getFirst().getFacultyName()));
        CourseDTO courseDTO = new CourseDTO();
        when(facultyService.getAllFaculties()).thenReturn(faculties);
        when(courseService.getAllCoursesDTO()).thenReturn(courses);

        mockMvc.perform(get("/course"))
                .andExpect(status().isOk())
                .andExpect(view().name("university/course"))
                .andExpect(model().attributeExists("courseDTO", "faculties", "courses"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    void addCourse_ShouldAddCourseAndRedirect() throws Exception {
        CourseDTO courseDTO = new CourseDTO(1L, "Test Course", "Test Description", 1L, "Test Faculty");

        doNothing().when(courseService).createCourse(any(CourseDTO.class));

        mockMvc.perform(post("/course/add")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", courseDTO.getName())
                        .param("description", courseDTO.getDescription())
                        .param("facultyId", String.valueOf(courseDTO.getFacultyId()))
                        .param("facultyName", String.valueOf(courseDTO.getFacultyName()))
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/course"));
    }
}
