package com.example.university.service.university;

import com.example.university.customexception.CourseNotFoundException;
import com.example.university.dto.GroupDTO;
import com.example.university.model.university.Course;
import com.example.university.model.university.Group;
import com.example.university.repository.CourseRepository;
import com.example.university.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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
        List<Group> groups = getAllGroups();
        List<GroupDTO> groupDTOs = new ArrayList<>();

        for (Group group : groups) {
            try {
                Course course = courseRepository.findById(group.getCourseId())
                        .orElseThrow(() -> new CourseNotFoundException("Course not found"));

                GroupDTO dto = new GroupDTO();
                dto.setId(group.getId());
                dto.setGroupName(group.getGroupName());
                dto.setCourseName(course.getName());
                groupDTOs.add(dto);
            } catch (NoSuchElementException e) {
                e.printStackTrace();
            }
        }
        return groupDTOs;
    }

    public void updateGroup(Long groupId, String groupName, Long courseId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new NoSuchElementException("Group not found"));
        group.setGroupName(groupName);
        group.setCourseId(courseId);
        groupRepository.save(group);
    }
}
