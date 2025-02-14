package com.grownited.service;

import com.grownited.entity.ClinicEntity;
import com.grownited.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicService {

    @Autowired
    private ClinicRepository clinicRepository;

    public List<ClinicEntity> getAllClinics() {
        return clinicRepository.findAll();
    }

    public Optional<ClinicEntity> getClinicById(Integer clinicId) {
        return clinicRepository.findById(clinicId);
    }

    public void saveClinic(ClinicEntity clinicEntity) {
        clinicRepository.save(clinicEntity);
    }

    public void deleteClinic(Integer clinicId) {
        clinicRepository.deleteById(clinicId);
    }
}
