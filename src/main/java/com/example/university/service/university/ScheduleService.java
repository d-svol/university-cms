package com.example.university.service.university;

import com.example.university.model.university.Schedule;
import com.example.university.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public List<Schedule> getAllSchedule() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getScheduleByGroupId(Long groupId) {
        return scheduleRepository.findScheduleByGroupId(groupId);
    }

    public List<Schedule> getScheduleByProfessorId(Long professorId) {
        if (professorId <= 0) {
            throw new IllegalArgumentException("Wrong value of 'Id'");
        }
        return scheduleRepository.findScheduleByProfessorId(professorId);
    }
}
