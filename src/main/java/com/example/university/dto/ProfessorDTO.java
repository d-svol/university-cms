package com.example.university.dto;

import lombok.*;

@Setter
@Getter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDTO {
    private Long professorId;
    private String userName;
    private String courseName;
}

