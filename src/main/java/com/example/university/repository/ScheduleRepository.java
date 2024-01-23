package com.example.university.repository;

import com.example.university.model.university.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findScheduleByGroupId(Long groupId);
    List<Schedule> findScheduleByProfessorId(Long professorId);
}
