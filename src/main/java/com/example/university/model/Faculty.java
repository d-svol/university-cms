package com.example.university.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Faculty {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long facultyId;

    private String name;

    @ManyToOne
    @JoinColumn(name = "head_of_faculty_id")
    private Professor headOfFaculty;

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL)
    private List<Professor> professors;

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL)
    private List<Student> students;

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL)
    private List<Course> courses;

    void addProfessor(Professor professor){
        professors.add(professor);
    }

    void addStudent(Student student){
        students.add(student);
    }

    void addCourse(Course course){
        courses.add(course);
    }


    public Long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public List<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(List<Professor> professors) {
        this.professors = professors;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}
