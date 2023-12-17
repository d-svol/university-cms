package com.example.university.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

public class Professor{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long professorId;
    private String firstName;
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @ManyToMany(mappedBy = "professor")
    private List<Course> courses;

    public List<ScheduleEntry> viewDailyTimetable(Date date) {
        return null;
    }

    public List<ScheduleEntry> viewMonthlyTimetable(int month, int year) {
        return null;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
