package com.example.university.dto;

import lombok.*;

@Setter
@Getter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private Long id;
    private String name;
    private String description;
    private Long facultyId;
    private String facultyName;
}
