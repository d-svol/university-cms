package com.example.university.repository;

import com.example.university.dto.GroupDTO;
import com.example.university.model.university.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    @Query("SELECT new com.example.university.dto.GroupDTO(g.id, g.groupName, c.name) " +
            "FROM Group g JOIN Course c ON g.courseId = c.id")
    List<GroupDTO> findAllGroupDTOs();

    Optional<Group> findByGroupName(String name);
}
