package com.grownited.service;

import com.grownited.entity.DoctorProfileEntity;
import com.grownited.repository.DoctorProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DoctorProfileService {

    @Autowired
    private DoctorProfileRepository doctorProfileRepository;

    public List<DoctorProfileEntity> getAllDoctorProfiles() {
        return doctorProfileRepository.findAll();
    }

    public List<DoctorProfileEntity> getAllDoctors() {
        return doctorProfileRepository.findAll();
    }

    public Optional<DoctorProfileEntity> getDoctorProfileById(Integer id) {
        return doctorProfileRepository.findById(id);
    }

    public void saveDoctorProfile(DoctorProfileEntity doctorProfile) {
        doctorProfileRepository.save(doctorProfile);
    }

    public void deleteDoctorProfile(Integer id) {
        doctorProfileRepository.deleteById(id);
    }

    // Updated method to return DoctorProfileEntity directly
    public DoctorProfileEntity getDoctorProfileByUserId(UUID userId) {
        Optional<DoctorProfileEntity> doctorProfile = doctorProfileRepository.findByUser_UserId(userId);
        return doctorProfile.orElse(null); // Return null if not found
    }
}