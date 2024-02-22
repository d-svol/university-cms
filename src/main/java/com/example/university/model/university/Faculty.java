package com.example.university.model.university;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "faculty")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String facultyName;

    @Column(name = "university_id")
    private Long universityId;

    public Faculty(Long id, String facultyName) {
        this.id = id;
        this.facultyName = facultyName;
    }
}
