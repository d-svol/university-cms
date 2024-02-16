package com.example.university.controller.university;

import com.example.university.model.university.Schedule;
import com.example.university.repository.CourseRepository;
import com.example.university.repository.GroupRepository;
import com.example.university.service.university.ScheduleService;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ScheduleController.class)
@WithMockUser(authorities = "ADMIN")
public class TestScheduleController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScheduleService scheduleService;
    @MockBean
    private CourseRepository courseRepository;
    @MockBean
    private GroupRepository groupRepository;
    @MockBean
    private ProfessorService professorService;

    @Test
    public void schedulePage_ShouldReturnSchedule() throws Exception {
        List<Schedule> schedules = Arrays.asList(
                new Schedule(1L, 1L, 1L, 1L, LocalTime.parse("09:00:00"), LocalTime.parse("2023-07-07T11:00:00"), LocalDate.parse("2023-07-07")),
                new Schedule(2L, 2L, 1L, 1L, LocalTime.parse("10:00:00"), LocalTime.parse("2023-07-08T12:00:00"), LocalDate.parse("2023-07-08"))
        );

        Mockito.when(scheduleService.getAllSchedule()).thenReturn(schedules);

        mockMvc.perform(MockMvcRequestBuilders.get("/schedule"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("/university/schedule"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("schedules"));
    }

    @Test
    void testEditSchedule_ShouldEditSchedule() throws Exception {
        doNothing().when(scheduleService).updateSchedule(any());

        mockMvc.perform(post("/schedule/edit")
                        .param("scheduleId", "1")
                        .param("groupName", "GroupName")
                        .param("professorName", "ProfessorName")
                        .param("courseName", "CourseName")
                        .param("startTime", "12:00")
                        .param("endTime", "13:00")
                        .param("date", "2024-07-07")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/schedule"));
    }

    @Test
    void testAddSchedule_ShouldAddSchedule() throws Exception {
        doNothing().when(scheduleService).addSchedule(any());

        mockMvc.perform(post("/schedule/add")
                        .queryParam("groupId", "1")
                        .queryParam("professorId", "2")
                        .queryParam("courseId", "3")
                        .queryParam("startTime", "12:00")
                        .queryParam("endTime", "13:00")
                        .queryParam("date", "2023-07-07")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/schedule"));
    }

}
