package com.example.university.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "students", schema = "university")
public class Student extends User {
    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "group_id")
    private Group group;

    public Student(Long id, String firstName, String lastName) {
        super(id, firstName, lastName);
    }
}
