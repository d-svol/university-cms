package com.example.university.controller.user;

import com.example.university.dto.StudentDTO;
import com.example.university.service.university.GroupService;
import com.example.university.service.user.StudentService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
class TestStudentController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;
    @MockBean
    private GroupService groupService;

    @Test
    @WithMockUser
    public void studentPage_ShouldReturnStudents() throws Exception {
        List<StudentDTO> students = List.of(
                new StudentDTO(1L, "NameA", 1L, "GroupA"),
                new StudentDTO(2L, "NameB", 2L, "GroupB")
        );
        Mockito.when(studentService.getAllStudents()).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders.get("/students"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("/users/students"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("students"));
    }

    @Test
    @WithMockUser
    public void editStudent_ShouldReturnStudents() throws Exception {
        doNothing().when(studentService).updateStudentGroup(any());

        mockMvc.perform(post("/students/edit")
                        .queryParam("studentId", "1")
                        .queryParam("studentName", "FirstName")
                        .queryParam("groupId", "2")
                        .queryParam("groupName", "SecondGroup")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/students"));
    }
}