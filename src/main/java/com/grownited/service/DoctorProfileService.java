package com.grownited.service;

import com.grownited.entity.DoctorProfileEntity;
import com.grownited.repository.DoctorProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorProfileService {

    private final DoctorProfileRepository doctorProfileRepository;

    public DoctorProfileService(DoctorProfileRepository doctorProfileRepository) {
        this.doctorProfileRepository = doctorProfileRepository;
    }

    public List<DoctorProfileEntity> getAllDoctorProfiles() {
        return doctorProfileRepository.findAll();
    }

    public Optional<DoctorProfileEntity> getDoctorProfileByUserId(Long userId) {
        return doctorProfileRepository.findByUserUserId(userId);
    }

    public Optional<DoctorProfileEntity> getDoctorProfileById(Long docProfileId) {
        return doctorProfileRepository.findById(docProfileId);
    }

    public void saveDoctorProfile(DoctorProfileEntity doctorProfile) {
        doctorProfileRepository.save(doctorProfile);
    }

    public void deleteDoctorProfile(Long docProfileId) {
        doctorProfileRepository.deleteById(docProfileId);
    }
}