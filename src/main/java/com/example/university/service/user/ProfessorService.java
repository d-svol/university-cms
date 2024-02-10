package com.example.university.service.user;

import com.example.university.customexception.EntityNotFoundException;
import com.example.university.dto.ProfessorDTO;
import com.example.university.model.university.Course;
import com.example.university.model.user.Professor;
import com.example.university.model.user.UserEntity;
import com.example.university.repository.CourseRepository;
import com.example.university.repository.ProfessorRepository;
import com.example.university.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProfessorService {
    private static final Logger log = LoggerFactory.getLogger(ProfessorService.class);
    private final ProfessorRepository professorRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public List<ProfessorDTO> getAllProfessors() {
        return professorRepository.findAllProfessors();
    }


    public List<ProfessorDTO> getAllProfessorDTOs() {
        List<Professor> professors = professorRepository.findAll();
        List<ProfessorDTO> professorDTOs = new ArrayList<>();

        for (Professor professor : professors) {
            try {
                UserEntity user = userRepository.findById(professor.getUserId())
                        .orElseThrow(() -> new EntityNotFoundException("User not found"));
                Course course = courseRepository.findById(professor.getCourseId())
                        .orElseThrow(() -> new EntityNotFoundException("Course not found"));

                ProfessorDTO dto = new ProfessorDTO();
                dto.setProfessorId(professor.getId());
                dto.setUserName(user.getUsername());
                dto.setCourseName(course.getName());

                professorDTOs.add(dto);
            } catch (EntityNotFoundException e) {
                log.error("Error while fetching data: {}", e.getMessage());
            }
        }

        return professorDTOs;
    }

    public void editProfessor(Long professorId, Long courseId) {
        Professor professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid professor ID"));

        professor.setCourseId(courseId);
        professorRepository.save(professor);
    }

}
