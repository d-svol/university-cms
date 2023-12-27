package com.example.university.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "groups", schema = "university")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "group_name")
    private String groupName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "groups_courses",
            schema = "university",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    @ToString.Exclude
    private List<Course> courses = new ArrayList<>();

    public Group(Long id, String groupName) {
        this.id = id;
        this.groupName = groupName;
    }
}
