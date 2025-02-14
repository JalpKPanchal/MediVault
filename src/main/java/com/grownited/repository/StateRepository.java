package com.grownited.repository;

import com.grownited.entity.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<StateEntity, Integer> {
    // Custom query methods can be added here if needed
}
