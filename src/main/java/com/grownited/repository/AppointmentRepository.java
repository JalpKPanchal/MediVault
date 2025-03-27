package com.grownited.repository;

import com.grownited.entity.AppointmentEntity;
import com.grownited.entity.AppointmentEntity.AppointmentStatus;
import com.grownited.entity.DoctorProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, UUID> {

    // Fetch appointments by patientId
    List<AppointmentEntity> findByPatientId(UUID patientId);

    // Fetch appointments by doctor
    List<AppointmentEntity> findByDoctor(DoctorProfileEntity doctor);

    // Fetch appointments by status
    List<AppointmentEntity> findByStatus(AppointmentStatus status);

    // Fetch appointments by doctor and status
    List<AppointmentEntity> findByDoctorAndStatus(DoctorProfileEntity doctor, AppointmentStatus status);
}