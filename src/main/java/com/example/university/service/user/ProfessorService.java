package com.example.university.service.user;

import com.example.university.customexception.CourseNotFoundException;
import com.example.university.customexception.UserNotFoundException;
import com.example.university.dto.ProfessorDTO;
import com.example.university.model.university.Course;
import com.example.university.model.user.Professor;
import com.example.university.model.user.UserEntity;
import com.example.university.repository.CourseRepository;
import com.example.university.repository.ProfessorRepository;
import com.example.university.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class ProfessorService {
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
                        .orElseThrow(() -> new UserNotFoundException("User not found"));
                Course course = courseRepository.findById(professor.getCourseId())
                        .orElseThrow(() -> new CourseNotFoundException("Course not found"));

                ProfessorDTO dto = new ProfessorDTO();
                dto.setProfessorId(professor.getId());
                dto.setUserName(user.getUsername());
                dto.setCourseName(course.getName());

                professorDTOs.add(dto);
            } catch (NoSuchElementException e) {
                e.printStackTrace();
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
