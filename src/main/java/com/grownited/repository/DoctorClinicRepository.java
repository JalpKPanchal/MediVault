package com.grownited.repository;

import com.grownited.entity.DoctorClinicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorClinicRepository extends JpaRepository<DoctorClinicEntity, Integer> {
}
