package com.example.university.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDTO {
    private Long professorId;
    private String userName;
    private List<String> courseNames;
}

