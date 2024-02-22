package com.example.university.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "student")
public class Student {
    @Id
    private Long id;
    private Long groupId;
}
