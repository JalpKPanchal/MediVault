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

    public List<DoctorClinicEntity> getClinicsByDoctorId(Long doctorId) {
        return doctorClinicRepository.findByDoctorUserId(doctorId);
    }

    public List<DoctorClinicEntity> getDoctorsByClinicId(Long clinicId) {
        return doctorClinicRepository.findByClinicClinicId(clinicId);
    }

    public void saveDoctorClinic(DoctorClinicEntity doctorClinic) {
        doctorClinicRepository.save(doctorClinic);
    }

    public void deleteDoctorClinic(Long id) {
        doctorClinicRepository.deleteById(id);
    }
}