package com.example.university.service.user;

import com.example.university.customexception.ProfessorNotFoundException;
import com.example.university.model.user.UserEntity;
import com.example.university.repository.UserRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService  {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public void saveUser(UserEntity user) {
        userRepository.save(user);
    }

    public Optional<UserEntity> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<UserEntity> findProfessorByUsername(String username) {
        if (StringUtils.isBlank(username)) {
            throw new IllegalArgumentException("Wrong value of 'username' ");
        }
        Optional<UserEntity> professor = userRepository.findProfessorByUsername(username);

        if (professor.isEmpty()) {
            throw new ProfessorNotFoundException("User not found");
        }

        return professor;
    }
}
