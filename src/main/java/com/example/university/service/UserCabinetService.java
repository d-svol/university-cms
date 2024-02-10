package com.example.university.service;

import com.example.university.customexception.EntityNotFoundException;
import com.example.university.model.StudentCabinetData;
import com.example.university.model.university.Group;
import com.example.university.model.user.Student;
import com.example.university.model.user.UserEntity;
import com.example.university.service.university.GroupService;
import com.example.university.service.user.StudentService;
import com.example.university.service.user.UserService;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserCabinetService {
    private final GroupService groupService;
    private final UserService userService;
    private final StudentService studentService;

    public StudentCabinetData getStudentCabinetData(String username) {
        if (StringUtils.isBlank(username)) {
            throw new EntityNotFoundException("Username can't be blank");
        }

        Optional<UserEntity> user = userService.getByUsername(username);
        if (user.isEmpty()) {
            throw new EntityNotFoundException("No user found with username: " + username);
        }

        Optional<Student> studentByUserId = studentService.getStudentByUserId(user.get().getId());
        if (studentByUserId.isEmpty()) {
            throw new EntityNotFoundException("No student found for user ID: " + user.get().getId());
        }

        Optional<Group> groupById = groupService.getGroupById(studentByUserId.get().getGroupId());
        if (groupById.isEmpty()) {
            throw new EntityNotFoundException("No group found with ID: " + studentByUserId.get().getGroupId());
        }
        StudentCabinetData cabinetData = new StudentCabinetData();
        cabinetData.setGroupId(groupById.get().getId());
        cabinetData.setGroupName(groupById.get().getGroupName());

        return cabinetData;
    }
}


