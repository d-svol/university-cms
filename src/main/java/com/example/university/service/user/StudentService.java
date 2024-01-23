package com.example.university.service.user;

import com.example.university.model.user.Student;
import com.example.university.repository.GroupRepository;
import com.example.university.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentByUserId(Long id){
        return studentRepository.findById(id);
    }

}
