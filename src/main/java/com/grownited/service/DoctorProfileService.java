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

    public Optional<DoctorProfileEntity> getDoctorProfileById(Integer docProfileId) {
        return doctorProfileRepository.findById(docProfileId);
    }

    public Optional<DoctorProfileEntity> getDoctorProfileByUserId(UUID userId) {
        return doctorProfileRepository.findByUserUserId(userId);
    }

    public DoctorProfileEntity saveDoctorProfile(DoctorProfileEntity doctorProfile) {
        return doctorProfileRepository.save(doctorProfile);
    }

    public void deleteDoctorProfile(Integer docProfileId) {
        doctorProfileRepository.deleteById(docProfileId);
    }
}