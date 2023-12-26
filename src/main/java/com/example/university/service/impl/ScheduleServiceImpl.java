package com.example.university.service.impl;


import com.example.university.model.Schedule;
import com.example.university.repository.ScheduleRepository;
import com.example.university.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public Optional<Schedule> findById(long id) {
        return scheduleRepository.findById(id);
    }

    @Override
    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    @Override
    public void save(Schedule entity) {
        scheduleRepository.save(entity);
    }

    @Override
    public void update(Schedule entity) {
        scheduleRepository.save(entity);
    }

    @Override
    public void delete(Schedule entity) {
        scheduleRepository.delete(entity);
    }

    @Override
    public void deleteById(long id) {
        scheduleRepository.deleteById(id);
    }
}
