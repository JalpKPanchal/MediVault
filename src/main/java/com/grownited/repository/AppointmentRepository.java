package com.grownited.repository;

import com.grownited.entity.AppointmentEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, UUID> {
}