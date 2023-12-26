package com.example.university.service.impl;

import com.example.university.model.Professor;
import com.example.university.repository.ProfessorRepository;
import com.example.university.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorServiceImpl implements ProfessorService {
    private final ProfessorRepository professorRepository;

    @Autowired
    public ProfessorServiceImpl(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Override
    public Optional<Professor> findById(long id) {
        return professorRepository.findById(id);
    }

    @Override
    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    @Override
    public void save(Professor entity) {
        professorRepository.save(entity);
    }

    @Override
    public void update(Professor entity) {
        professorRepository.save(entity);
    }

    @Override
    public void delete(Professor entity) {
        professorRepository.delete(entity);
    }

    @Override
    public void deleteById(long id) {
        professorRepository.deleteById(id);
    }
}
