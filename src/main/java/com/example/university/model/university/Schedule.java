package com.example.university.model.university;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "group_id")
    @NonNull
    private Long groupId;

    @Column(name = "professor_id")
    @NonNull
    private Long professorId;

    @Column(name = "course_id")
    @NonNull
    private Long courseId;

    @Column(name = "start_time")
    @NonNull
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Column(name = "date")
    @NonNull
    private LocalDate date;
}
