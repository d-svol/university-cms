package com.example.university.service.impl;


import com.example.university.model.Course;
import com.example.university.repository.CourseRepository;
import com.example.university.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    private CourseRepository courseRepository;

    @Override
    public Optional<Course> findById(long id) {
        return courseRepository.findById(id);
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public void save(Course entity) {
        courseRepository.save(entity);
    }

    @Override
    public void update(Course entity) {
        courseRepository.save(entity);
    }

    @Override
    public void delete(Course entity) {
        courseRepository.delete(entity);
    }

    @Override
    public void deleteById(long id) {
        courseRepository.deleteById(id);
    }
}
