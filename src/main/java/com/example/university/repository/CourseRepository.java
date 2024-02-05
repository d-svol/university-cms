package com.example.university.repository;

import com.example.university.dto.CourseDTO;
import com.example.university.model.university.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("SELECT new com.example.university.dto.CourseDTO(c.id, c.name, c.description, f.id, f.facultyName) " +
            "FROM Course c " +
            "JOIN Faculty f ON c.facultyId = f.id")
    List<CourseDTO> findAllCourseDTOs();
}
