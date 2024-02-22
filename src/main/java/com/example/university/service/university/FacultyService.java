package com.example.university.service.university;

import com.example.university.model.university.Faculty;
import com.example.university.repository.FacultyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class FacultyService {
    private final FacultyRepository facultyRepository;


    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    public void addFaculty(Faculty faculty) {
        facultyRepository.save(faculty);
    }

    public void updateFaculty(Faculty faculty) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(faculty.getId());
        Faculty editFaculty = optionalFaculty.orElseThrow(() -> new EntityNotFoundException("Faculty not found with id: " + faculty.getId()));
        editFaculty.setFacultyName(faculty.getFacultyName());
        facultyRepository.save(editFaculty);
    }

    public void deleteFacultyById(Long id) {
        facultyRepository.deleteById(id);
    }

    public Optional<Faculty> getFacultyById(Long id) {
        return facultyRepository.findById(id);
    }
}
