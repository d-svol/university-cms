package com.example.university.controller.university;

import com.example.university.dto.ScheduleDTO;
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
import java.time.LocalTime;
import java.util.ArrayList;
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
        List<ScheduleDTO> schedules = new ArrayList<>();
        schedules.add(new ScheduleDTO(1L, "GroupA", "ProfessorA", "Math", LocalTime.of(12, 0), LocalTime.of(14, 0), LocalDate.of(2022, 1, 1)));
        Mockito.when(scheduleService.getFilteredSchedules(Mockito.any(LocalDate.class), Mockito.any(LocalDate.class))).thenReturn(schedules);

        mockMvc.perform(MockMvcRequestBuilders.get("/schedules")
                        .param("startDate", "2022-01-01")
                        .param("endDate", "2022-01-31"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("university/schedules"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("scheduleDTO"));
    }

    @Test
    void testEditSchedule_ShouldEditSchedule() throws Exception {
        doNothing().when(scheduleService).updateSchedule(any());

        mockMvc.perform(post("/schedules/edit")
                        .param("scheduleId", "1")
                        .param("groupName", "GroupName")
                        .param("professorName", "ProfessorName")
                        .param("courseName", "CourseName")
                        .param("startTime", "12:00")
                        .param("endTime", "13:00")
                        .param("date", "2024-07-07")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/schedules"));
    }

    @Test
    void testAddSchedule_ShouldAddSchedule() throws Exception {
        doNothing().when(scheduleService).addSchedule(any());

        mockMvc.perform(post("/schedules/add")
                        .queryParam("groupId", "1")
                        .queryParam("professorId", "2")
                        .queryParam("courseId", "3")
                        .queryParam("startTime", "12:00")
                        .queryParam("endTime", "13:00")
                        .queryParam("date", "2023-07-07")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/schedules"));
    }

}
