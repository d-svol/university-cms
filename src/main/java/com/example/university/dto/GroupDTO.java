package com.example.university.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDTO {
    private Long id;
    private String groupName;
    private String courseName;
}
