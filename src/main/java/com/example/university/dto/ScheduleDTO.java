package com.example.university.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Setter
@Getter
@Data
@Builder
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
