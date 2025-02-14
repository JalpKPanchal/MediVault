package com.grownited.service;

import com.grownited.entity.DoctorClinicEntity;
import com.grownited.repository.DoctorClinicRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorClinicService {

    private final DoctorClinicRepository doctorClinicRepository;

    public DoctorClinicService(DoctorClinicRepository doctorClinicRepository) {
        this.doctorClinicRepository = doctorClinicRepository;
    }

    public List<DoctorClinicEntity> getAllDoctorClinics() {
        return doctorClinicRepository.findAll();
    }

    public void saveDoctorClinic(DoctorClinicEntity entity) {
        doctorClinicRepository.save(entity);
    }

    public void deleteDoctorClinic(Integer id) {
        doctorClinicRepository.deleteById(id);
    }
}
