package com.example.university.service.user;

import com.example.university.model.user.Role;
import com.example.university.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }

    public Optional<Role> findRoleById(Long id){
        return roleRepository.findById(id);
    }
}


