package com.example.university.service.university;

import com.example.university.customexception.EntityNotFoundException;
import com.example.university.dto.ProfessorDTO;
import com.example.university.dto.ScheduleDTO;
import com.example.university.model.university.Course;
import com.example.university.model.university.Group;
import com.example.university.model.university.Schedule;
import com.example.university.repository.CourseRepository;
import com.example.university.repository.GroupRepository;
import com.example.university.repository.ScheduleRepository;
import com.example.university.service.user.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ProfessorService professorService;
    private final CourseRepository courseRepository;
    private final GroupRepository groupRepository;

    public List<Schedule> getAllSchedule() {
        return scheduleRepository.findAll();
    }

    public List<ScheduleDTO> getAllScheduleDTO() {
        List<Schedule> schedule = scheduleRepository.findAll();
        return schedule.stream().map(this::convertToDTO).toList();
    }

    public void updateSchedule(ScheduleDTO scheduleDTO) {
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(scheduleDTO.getScheduleId());
        if (optionalSchedule.isPresent()) {
            Schedule schedule = optionalSchedule.get();
            schedule.setStartTime(scheduleDTO.getStartTime());
            schedule.setEndTime(scheduleDTO.getEndTime());
            schedule.setDate(scheduleDTO.getDate());
            scheduleRepository.save(schedule);
        } else {
            throw new EntityNotFoundException("Schedule not found with id: " + scheduleDTO.getScheduleId());
        }
    }

    public void addSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    public void deleteScheduleById(Long id) {
        scheduleRepository.deleteById(id);
    }

    private ScheduleDTO convertToDTO(Schedule schedule) {
        String groupName = groupRepository.findById(schedule.getGroupId()).map(Group::getGroupName)
                .orElseThrow(() -> new EntityNotFoundException("Group not found by Id: " + schedule.getGroupId()));

        String professorName = Optional.ofNullable(professorService.getProfessorDTOById(schedule.getProfessorId()))
                .map(ProfessorDTO::getUserName)
                .orElseThrow(() -> new EntityNotFoundException("Invalid professor ID: " + schedule.getProfessorId()));
        String courseName = courseRepository.findById(schedule.getCourseId())
                .map(Course::getName)
                .orElseThrow(() -> new EntityNotFoundException("Invalid course ID: " + schedule.getCourseId()));

        return new ScheduleDTO(
                schedule.getId(),
                groupName,
                professorName,
                courseName,
                schedule.getStartTime(),
                schedule.getEndTime(),
                schedule.getDate()
        );
    }
}
