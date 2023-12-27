package com.example.university.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "courses", schema = "university")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course_name")
    private String courseName;

    @ManyToMany
    @JoinTable(name = "professors_courses",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id")
    )
    @ToString.Exclude
    private Collection<Professor> professors;

    public Course(Long id, String name) {
        this.id = id;
        this.courseName = name;
    }
}
