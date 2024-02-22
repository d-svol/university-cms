package com.example.university.service.user;

import com.example.university.dto.ProfessorDTO;
import com.example.university.model.university.Course;
import com.example.university.model.user.Professor;
import com.example.university.repository.CourseRepository;
import com.example.university.repository.ProfessorRepository;
import com.example.university.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService {
    private final ProfessorRepository professorRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public List<ProfessorDTO> getAllProfessorDTOs() {
        List<Professor> professors = professorRepository.findAll();
        return professors.stream().map(this::convertToDTO).toList();
    }

    public ProfessorDTO getProfessorDTOById(Long id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Professor not found with id: " + id));
        return convertToDTO(professor);
    }

    public void editProfessor(Long professorId, Long courseId) {
        Professor professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new EntityNotFoundException("Invalid professor ID"));

        Course courseToAdd = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Invalid course ID"));

        boolean alreadyTeaches = professor.getCourses().stream()
                .anyMatch(course -> course.getId().equals(courseId));

        if (!alreadyTeaches) {
            professor.getCourses().add(courseToAdd);
            professorRepository.save(professor);
        } else {
            throw new EntityNotFoundException("Professor already teaches this course");
        }
    }

    private ProfessorDTO convertToDTO(Professor professor) {
        ProfessorDTO dto = new ProfessorDTO();
        dto.setProfessorId(professor.getId());

        userRepository.findById(professor.getUserId()).ifPresent(user -> dto.setUserName(user.getUsername()));

        List<String> courseNames = professor.getCourses().stream()
                .map(Course::getName)
                .toList();
        dto.setCourseNames(courseNames);

        return dto;
    }
}
