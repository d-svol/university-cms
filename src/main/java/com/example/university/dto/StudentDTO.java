package com.example.university.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    public Long studentId;
    public String studentName;
    public String groupName;
    private Long groupId;
}
