package com.example.university.service.university;

import com.example.university.model.university.University;
import com.example.university.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UniversityService {
    private final UniversityRepository universityRepository;

    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }
}

