package com.example.university.repository;

import com.example.university.dto.StudentDTO;
import com.example.university.model.user.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT new com.example.university.dto.StudentDTO(s.id, u.username, s.groupId, g.groupName) " +
            "FROM Student s " +
            "JOIN UserEntity u ON s.id = u.id  " +
            "JOIN Group g ON s.groupId = g.id " +
            "WHERE u.username = :username")
    StudentDTO getStudentDTOByName(@Param("username") String username);
}
