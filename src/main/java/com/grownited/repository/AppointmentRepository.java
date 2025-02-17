package com.grownited.repository;

import com.grownited.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, UUID> {
}
