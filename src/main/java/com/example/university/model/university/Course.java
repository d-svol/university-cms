package com.example.university.model.university;

import com.example.university.model.user.Professor;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "courses")
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;
    private String description;

    @Column(name = "faculty_id", nullable = false)
    private Long facultyId;

    @ManyToMany(mappedBy = "courses")
    private List<Professor> professors;

    public Course(Long id, @NonNull String name) {
        this.id = id;
        this.name = name;
    }

    public Course(Long id, @NonNull String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
