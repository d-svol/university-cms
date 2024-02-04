package com.example.university.repository;

import com.example.university.dto.ProfessorDTO;
import com.example.university.model.user.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    @Query("SELECT new com.example.university.dto.ProfessorDTO(p.id, u.username, c.name) FROM Professor p " +
            "JOIN UserEntity u ON p.userId = u.id " +
            "JOIN Course c ON p.courseId = c.id")
    List<ProfessorDTO> findAllProfessors();

    @Query("SELECT new com.example.university.dto.ProfessorDTO(p.id, u.username, c.name) FROM Professor p " +
            "JOIN UserEntity u ON p.userId = u.id " +
            "JOIN Course c ON p.courseId = c.id " +
            "WHERE p.id = :professorId")
    ProfessorDTO findProfessorDTOById(@Param("professorId") Long professorId);

}
