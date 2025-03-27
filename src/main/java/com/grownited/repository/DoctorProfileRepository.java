package com.grownited.repository;

import com.grownited.entity.DoctorProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorProfileRepository extends JpaRepository<DoctorProfileEntity, Long> {
    Optional<DoctorProfileEntity> findByUserUserId(Long userId);
}