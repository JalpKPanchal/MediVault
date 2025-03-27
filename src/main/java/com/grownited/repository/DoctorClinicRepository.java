package com.grownited.repository;

import com.grownited.entity.DoctorClinicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorClinicRepository extends JpaRepository<DoctorClinicEntity, Long> {
    List<DoctorClinicEntity> findByDoctorUserId(Long doctorId);
    List<DoctorClinicEntity> findByClinicClinicId(Long clinicId);
}