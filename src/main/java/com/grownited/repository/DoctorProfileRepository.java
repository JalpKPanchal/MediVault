package com.grownited.repository;

import com.grownited.entity.DoctorProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DoctorProfileRepository extends JpaRepository<DoctorProfileEntity, Integer> {

    Optional<DoctorProfileEntity> findByUser_UserId(UUID userId);
}