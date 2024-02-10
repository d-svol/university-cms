package com.example.university.dto;

import lombok.*;

@Setter
@Getter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    public Long studentId;
    public String studentName;
    private Long groupId;
    public String groupName;
}
