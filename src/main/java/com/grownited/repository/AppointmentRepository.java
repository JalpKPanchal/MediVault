package com.grownited.repository;

import com.grownited.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
    List<AppointmentEntity> findByPatientId(Long patientId);

    @Query("SELECT a FROM AppointmentEntity a WHERE a.doctor.user.userId = :doctorId")
    List<AppointmentEntity> findByDoctorUserId(@Param("doctorId") Long doctorId);
}