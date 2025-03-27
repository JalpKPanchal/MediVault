package com.grownited.service;

import com.grownited.entity.ClinicEntity;
import com.grownited.repository.ClinicRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicService {

    private final ClinicRepository clinicRepository;

    public ClinicService(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    public List<ClinicEntity> getAllClinics() {
        return clinicRepository.findAll();
    }

    public Optional<ClinicEntity> getClinicById(Long clinicId) {
        return clinicRepository.findById(clinicId);
    }

    public void saveClinic(ClinicEntity clinic) {
        clinicRepository.save(clinic);
    }

    public void deleteClinic(Long clinicId) {
        clinicRepository.deleteById(clinicId);
    }
}