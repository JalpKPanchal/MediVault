package com.grownited.repository;

import com.grownited.entity.StateEntity;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<StateEntity,UUID> {
    // Custom query methods can be added here if needed
}
