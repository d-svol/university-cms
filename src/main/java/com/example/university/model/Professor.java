package com.example.university.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Collection;


@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "professors")
public class Professor extends User {
    @Column(name = "faculty")
    private String faculty;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "professors_courses",
            schema = "university",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    @ToString.Exclude
    private Collection<Course> courses;
}
