package com.example.university.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

public class Student{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    private String firstName;

    private String lastName;

    @ManyToMany(mappedBy = "students")
    private List<Course> courses;

    public List<ScheduleEntry> viewDailyTimetable(Date date) {
        return null;
    }

    public List<ScheduleEntry> viewMonthlyTimetable(int month, int year) {
        return null;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
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


    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
