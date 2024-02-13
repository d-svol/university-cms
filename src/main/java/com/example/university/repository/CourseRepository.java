package com.example.university.repository;

import com.example.university.dto.CourseDTO;
import com.example.university.model.university.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("SELECT new com.example.university.dto.CourseDTO(c.id, c.name, c.description, f.id, f.facultyName) " +
            "FROM Course c " +
            "JOIN Faculty f ON c.facultyId = f.id")
    List<CourseDTO> findAllCourseDTOs();

    @Query("SELECT c FROM Course c JOIN c.professors p JOIN UserEntity u ON p.userId = u.id WHERE u.username = :username")
    List<Course> findAllCoursesByProfessorUsername(@Param("username") String username);
}
