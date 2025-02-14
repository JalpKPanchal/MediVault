package com.grownited.service;

import com.grownited.entity.DoctorProfileEntity;
import com.grownited.repository.DoctorProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorProfileService {

    @Autowired
    private DoctorProfileRepository doctorProfileRepository;

    // Create or Update
    public DoctorProfileEntity saveDoctorProfile(DoctorProfileEntity doctorProfileEntity) {
        return doctorProfileRepository.save(doctorProfileEntity);
    }

    // Get by ID
    public Optional<DoctorProfileEntity> getDoctorProfileById(Integer docProfileId) {
        return doctorProfileRepository.findById(docProfileId);
    }

    // Get all Doctor Profiles
    public List<DoctorProfileEntity> getAllDoctorProfiles() {
        return doctorProfileRepository.findAll();
    }

    // Delete by ID
    public void deleteDoctorProfile(Integer docProfileId) {
        doctorProfileRepository.deleteById(docProfileId);
    }
}
