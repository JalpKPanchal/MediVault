package com.grownited.repository;

import com.grownited.entity.UserEntity;
import com.grownited.entity.UserEntity.Role;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,UUID> {

	Optional<UserEntity> findByEmail(String email);
    // Custom query methods can be added here if needed

	List<UserEntity> findByRole(Role doctor);
}
