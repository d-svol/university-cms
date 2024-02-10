package com.example.university.service.user;

import com.example.university.customexception.EntityNotFoundException;
import com.example.university.dto.UserDTO;
import com.example.university.model.user.Role;
import com.example.university.model.user.UserEntity;
import com.example.university.repository.UserRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public List<UserDTO> getAllUsersDTO() {
        List<UserEntity> users = userRepository.findAll();
        return users.stream().map(this::convertToUserDTO).collect(Collectors.toList());
    }

    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void addUser(UserDTO userDTO) {
        UserEntity user = new UserEntity();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        Role role = roleService.getRoleByName(userDTO.getRoleName())
                .orElseThrow(() -> new EntityNotFoundException("Role not found: " + userDTO.getRoleName()));
        user.setRole(role);
        userRepository.save(user);
    }

    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

    public void updateUser(UserDTO userDTO) {
        Optional<UserEntity> existingUser = userRepository.findByUsername(userDTO.getUsername());

        if (existingUser.isPresent() && !existingUser.get().getUsername().equals(userDTO.getUsername())) {
            throw new EntityNotFoundException("Username already taken!");
        } else {
            UserEntity user = existingUser.orElse(new UserEntity());
            user.setUsername(userDTO.getUsername());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            Role role = roleService.getRoleByName(userDTO.getRoleName())
                    .orElseThrow(() -> new EntityNotFoundException("Role not found: " + userDTO.getRoleName()));
            user.setRole(role);
            userRepository.save(user);
        }
    }


    public Optional<UserEntity> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<UserDTO> getUserDTOByUsername(String username) {
        return userRepository.findUserDTOByUsername(username);
    }

    public Optional<UserEntity> findProfessorByUsername(String username) {
        if (StringUtils.isBlank(username)) {
            throw new IllegalArgumentException("Wrong value of 'username' ");
        }
        Optional<UserEntity> professor = userRepository.findProfessorByUsername(username);

        if (professor.isEmpty()) {
            throw new EntityNotFoundException("User not found");
        }

        return professor;
    }

    private UserDTO convertToUserDTO(UserEntity user) {
        UserDTO dto = new UserDTO();
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        if (user.getRole() != null) {
            dto.setRoleName(user.getRole().getName());
        }

        return dto;
    }
}
