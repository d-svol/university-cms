package com.example.university.model.university;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "name")
    private String groupName;
}
