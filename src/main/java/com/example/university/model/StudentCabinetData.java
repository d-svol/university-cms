package com.example.university.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentCabinetData {
//    private Long userID;
//    private String username;
    private Long studentId;
    private Long groupId;
    private String groupName;
//    private List<Group> availableGroups;
}
