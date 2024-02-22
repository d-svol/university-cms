package com.example.university.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private Long id;
    private String name;
    private String description;
    private Long facultyId;
    private String facultyName;
}
