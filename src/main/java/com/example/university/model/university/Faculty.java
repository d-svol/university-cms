package com.example.university.model.university;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "faculty")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String facultyName;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;


    public Faculty(long id, String facultyName) {
        this.id = id;
        this.facultyName = facultyName;
    }
}
