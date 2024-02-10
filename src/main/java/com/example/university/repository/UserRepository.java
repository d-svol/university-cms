package com.example.university.repository;

import com.example.university.dto.UserDTO;
import com.example.university.model.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT u FROM UserEntity u WHERE u.username = :username AND u.role.id = 3")
    Optional<UserEntity> findProfessorByUsername(String username);

    Optional<UserDTO> findUserDTOByUsername(String username);
    Optional<UserEntity> findByUsername(String username);
}

