package com.example.university.service.user;

import com.example.university.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StuffService {
    private final UserRepository userRepository;

    @Autowired
    public StuffService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
