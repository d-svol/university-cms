package com.example.university.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "schedule", schema = "university")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long scheduleId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Professor professor;

    @OneToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(name = "schedule_start")
    private LocalDateTime scheduleStart;

    @Column(name = "schedule_end")
    private LocalDateTime scheduleEnd;

    @Column(name = "schedule_description")
    private String scheduleDescription;
}
