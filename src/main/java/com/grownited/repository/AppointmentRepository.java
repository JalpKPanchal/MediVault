package com.grownited.repository;

import com.grownited.entity.AppointmentEntity;
import com.grownited.entity.DoctorProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, UUID> {

    List<AppointmentEntity> findByPatientId(UUID patientId);

    // ✅ Find appointments by DoctorProfileEntity instead of ID
    List<AppointmentEntity> findByDoctor(DoctorProfileEntity doctor);
}
