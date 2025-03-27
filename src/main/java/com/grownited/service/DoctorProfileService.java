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

    // Fetch all doctor profiles (removed redundant getAllDoctors method)
    public List<DoctorProfileEntity> getAllDoctorProfiles() {
        return doctorProfileRepository.findAll();
    }

    // Fetch a doctor profile by ID
    public Optional<DoctorProfileEntity> getDoctorProfileById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Doctor profile ID cannot be null");
        }
        return doctorProfileRepository.findById(id);
    }

    // Save or update a doctor profile
    public DoctorProfileEntity saveDoctorProfile(DoctorProfileEntity doctorProfile) {
        if (doctorProfile == null) {
            throw new IllegalArgumentException("Doctor profile cannot be null");
        }
        return doctorProfileRepository.save(doctorProfile);
    }

    // Delete a doctor profile by ID
    public void deleteDoctorProfile(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Doctor profile ID cannot be null");
        }
        if (!doctorProfileRepository.existsById(id)) {
            throw new IllegalStateException("Doctor profile with ID " + id + " not found");
        }
        doctorProfileRepository.deleteById(id);
    }

    // Fetch a doctor profile by user ID
    public Optional<DoctorProfileEntity> getDoctorProfileByUserId(UUID userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        return doctorProfileRepository.findByUserUserId(userId); // Note: Updated method name in repository
    }
}