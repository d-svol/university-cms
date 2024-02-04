package com.example.university.service.university;

import com.example.university.customexception.CourseNotFoundException;
import com.example.university.customexception.FacultyNotFoundException;
import com.example.university.dto.CourseDTO;
import com.example.university.model.university.Course;
import com.example.university.model.university.Faculty;
import com.example.university.repository.CourseRepository;
import com.example.university.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final FacultyRepository facultyRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, FacultyRepository facultyRepository) {
        this.courseRepository = courseRepository;
        this.facultyRepository = facultyRepository;
    }

    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAllCourseDTOs();
    }

    public void createCourse(CourseDTO courseDTO) {
        Faculty faculty = facultyRepository.findById(courseDTO.getFacultyId())
                .orElseThrow(() -> new FacultyNotFoundException("Faculty not found by Id"));
        Course course = new Course();
        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());
        course.setFaculty(faculty);
        courseRepository.save(course);
    }

    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    public void updateCourse(CourseDTO courseDTO) {
        Course course = courseRepository.findById(courseDTO.getId())
                .orElseThrow(() -> new CourseNotFoundException("Invalid course ID: " + courseDTO.getId()));
        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());
        Faculty faculty = facultyRepository.findById(courseDTO.getFacultyId())
                .orElseThrow(() -> new FacultyNotFoundException("Invalid faculty ID: " + courseDTO.getFacultyId()));
        course.setFaculty(faculty);
        courseRepository.save(course);
    }
}

