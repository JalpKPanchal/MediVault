package com.grownited.repository;

import com.grownited.entity.PatientMedicalHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientMedicalHistoryRepository extends JpaRepository<PatientMedicalHistoryEntity, Long> {
    List<PatientMedicalHistoryEntity> findByPatient_UserId(Long userId);
}