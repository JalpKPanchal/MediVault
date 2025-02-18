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

    // ✅ Save or update doctor profile
    public DoctorProfileEntity saveDoctorProfile(DoctorProfileEntity doctorProfile) {
        return doctorProfileRepository.save(doctorProfile);
    }

    // ✅ Fetch all doctor profiles
    public List<DoctorProfileEntity> getAllDoctorProfiles() {
        return doctorProfileRepository.findAll();
    }

    // ✅ Fetch doctor profile by ID
    public Optional<DoctorProfileEntity> getDoctorProfileById(Integer docProfileId) {
        return doctorProfileRepository.findById(docProfileId);
    }

    // ✅ Fetch doctor profile by user ID
    public Optional<DoctorProfileEntity> getDoctorProfileByUserId(UUID userId) {
        return doctorProfileRepository.findByUser_UserId(userId);
    }

    // ✅ Delete doctor profile by ID
    public void deleteDoctorProfile(Integer docProfileId) {
        doctorProfileRepository.deleteById(docProfileId);
    }
 // ✅ Fetch all doctors
    public List<DoctorProfileEntity> getAllDoctors() {
        return doctorProfileRepository.findAll();
    }
}
