package com.example.university.service.user;

import com.example.university.customexception.EntityNotFoundException;
import com.example.university.dto.StudentDTO;
import com.example.university.model.university.Group;
import com.example.university.model.user.Student;
import com.example.university.model.user.UserEntity;
import com.example.university.repository.GroupRepository;
import com.example.university.repository.StudentRepository;
import com.example.university.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(this::convertToStudentDTO)
                .collect(Collectors.toList());
    }

    public void updateStudentGroup(StudentDTO studentDTO) {
        Student student = studentRepository.findById(studentDTO.studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with name " + studentDTO.studentName));
        Group group = groupRepository.findByGroupName(studentDTO.groupName)
                .orElseThrow(() -> new EntityNotFoundException("Group not found with name " + studentDTO.groupName));

        student.setGroupId(group.getId());
        studentRepository.save(student);
    }

    private StudentDTO convertToStudentDTO(Student student) {
        UserEntity user = Optional.ofNullable(userRepository.findById(student.getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found for student with " + student.getId()))).get();

        Group group = Optional.ofNullable(groupRepository.findById(student.getGroupId())
                .orElseThrow(() -> new EntityNotFoundException("Group not found with id " + student.getGroupId()))).get();

        return new StudentDTO(user.getId(), user.getUsername(), group.getId(), group.getGroupName());
    }

    public Optional<Student> getStudentByUserId(Long id) {
        return studentRepository.findById(id);
    }
}
