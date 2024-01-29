package com.example.university.service.university;

import com.example.university.customexception.CourseNotFoundException;
import com.example.university.model.university.Course;
import com.example.university.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long courseId) {
        return courseRepository.findById(courseId);
    }

    public Optional<Course> getCourseByName(String courseName) {
        return courseRepository.findCourseByName(courseName);
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public void addCourse(Course course) {
        courseRepository.save(course);
    }

    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    public void updateCourse(Course course) {
        Course existingCourse = courseRepository.findById(course.getId())
                .orElseThrow(() -> new IllegalArgumentException("Course not found with id: " + course.getId()));

        existingCourse.setName(course.getName());
        existingCourse.setDescription(course.getDescription());
        existingCourse.setFaculty(course.getFaculty());

        courseRepository.save(existingCourse);
    }
}

