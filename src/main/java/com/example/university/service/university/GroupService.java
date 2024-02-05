package com.example.university.service.university;

import com.example.university.customexception.GroupNotFoundException;
import com.example.university.dto.GroupDTO;
import com.example.university.model.university.Course;
import com.example.university.model.university.Group;
import com.example.university.repository.CourseRepository;
import com.example.university.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Optional<Group> getGroupById(Long id) {
        return groupRepository.findById(id);
    }

    public List<GroupDTO> getAllGroupsDTOs() {
        return groupRepository.findAllGroupDTOs();
    }

    public void addGroup(String groupName, Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new GroupNotFoundException("Course not found for ID: " + courseId));
        Group group = new Group();
        group.setGroupName(groupName);
        group.setCourseId(course.getId());
        groupRepository.save(group);
    }

    public void updateGroup(Long groupId, String groupName, Long courseId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException("Group not found by Id: " + groupId));
        group.setGroupName(groupName);
        group.setCourseId(courseId);
        groupRepository.save(group);
    }

    public void deleteGroup(Long groupId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException("Group not found by Id: " + groupId));
        groupRepository.delete(group);
    }
}
