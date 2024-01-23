package com.example.university.service.user;

import com.example.university.model.user.Professor;
import com.example.university.repository.ProfessorRepository;
import com.example.university.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;
    private final UserRepository userRepository;

    public List<Professor> getAllProfessors() {
        return professorRepository.findAll();
    }


}
