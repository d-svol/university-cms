package com.example.university.service.impl;


import com.example.university.model.Group;
import com.example.university.repository.GroupRepository;
import com.example.university.service.GroupService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {
    private GroupRepository groupRepository;

    @Override
    public Optional<Group> findById(long id) {
        return groupRepository.findById(id);
    }

    @Override
    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    @Override
    public void save(Group entity) {
        groupRepository.save(entity);
    }

    @Override
    public void update(Group entity) {
        groupRepository.save(entity);
    }

    @Override
    public void delete(Group entity) {
        groupRepository.delete(entity);
    }

    @Override
    public void deleteById(long id) {
        groupRepository.deleteById(id);
    }
}
