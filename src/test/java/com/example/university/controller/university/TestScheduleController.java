package com.example.university.controller.university;

import com.example.university.model.university.Schedule;
import com.example.university.service.university.ScheduleService;
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
import java.util.Arrays;
import java.util.List;

@WebMvcTest(ScheduleController.class)
@WithMockUser(authorities = "ADMIN")
public class TestScheduleController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScheduleService scheduleService;

    @Test
    public void schedulePage_ShouldReturnSchedule() throws Exception {
        List<Schedule> schedules = Arrays.asList(
                new Schedule(1L, 1L, 1L, 1L, LocalDateTime.parse("2023-07-07T09:00:00"), LocalDateTime.parse("2023-07-07T11:00:00"), LocalDate.parse("2023-07-07")),
                new Schedule(2L, 2L, 1L, 1L, LocalDateTime.parse("2023-07-08T10:00:00"), LocalDateTime.parse("2023-07-08T12:00:00"), LocalDate.parse("2023-07-08"))
        );

        Mockito.when(scheduleService.getAllSchedule()).thenReturn(schedules);

        mockMvc.perform(MockMvcRequestBuilders.get("/schedule"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("/university/schedule"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("schedules"));
    }
}
