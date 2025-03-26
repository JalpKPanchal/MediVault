package com.grownited.repository;

import com.grownited.entity.AppointmentEntity;
import com.grownited.entity.DoctorProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, UUID> {

    List<AppointmentEntity> findByPatientId(UUID patientId);

    List<AppointmentEntity> findByDoctor(DoctorProfileEntity doctor);

    List<AppointmentEntity> findByStatus(AppointmentEntity.AppointmentStatus status);

    // New method to find by doctor and status
    List<AppointmentEntity> findByDoctorAndStatus(DoctorProfileEntity doctor, AppointmentEntity.AppointmentStatus status);
}