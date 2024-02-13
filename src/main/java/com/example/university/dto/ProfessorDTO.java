package com.example.university.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDTO {
    private Long professorId;
    private String userName;
    private List<String> courseNames;
}

