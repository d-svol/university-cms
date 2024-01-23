package com.example.university.service.university;

import com.example.university.model.university.Faculty;
import com.example.university.repository.FacultyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class FacultyService {
    private final FacultyRepository facultyRepository;


    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

//    public Optional<Faculty> getFacultyById(Long id) {
//        return facultyRepository.findById(id);
//    }
//
//    public Faculty saveFaculty(Faculty faculty) {
//        return facultyRepository.save(faculty);
//    }
//
//    public void deleteFaculty(Long id) {
//        facultyRepository.deleteById(id);
//    }
}
