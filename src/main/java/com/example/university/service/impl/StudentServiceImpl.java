package com.example.university.service.impl;

import com.example.university.model.Student;
import com.example.university.repository.StudentRepository;
import com.example.university.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Optional<Student> findById(long id) {
        return studentRepository.findById(id);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public void save(Student entity) {
        studentRepository.save(entity);
    }

    @Override
    public void update(Student entity) {
        studentRepository.save(entity);
    }

    @Override
    public void delete(Student entity) {
        studentRepository.delete(entity);
    }

    @Override
    public void deleteById(long id) {

    }
}
