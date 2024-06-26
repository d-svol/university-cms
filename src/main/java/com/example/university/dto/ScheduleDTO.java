package com.example.university.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDTO {
    private Long scheduleId;
    private String groupName;
    private String professorName;
    private String courseName;
    private LocalTime startTime;
    private LocalTime endTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
}
