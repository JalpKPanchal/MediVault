package com.grownited.service;

import com.grownited.entity.PatientMedicalHistoryEntity;
import com.grownited.repository.PatientMedicalHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientMedicalHistoryService {

    @Autowired
    private PatientMedicalHistoryRepository repository;

    public PatientMedicalHistoryEntity saveMedicalHistory(PatientMedicalHistoryEntity history) {
        return repository.save(history);
    }

    public List<PatientMedicalHistoryEntity> getMedicalHistoryByPatientId(UUID patientId) {
        return repository.findByPatient_UserId(patientId);
    }

    public Optional<PatientMedicalHistoryEntity> getMedicalHistoryById(Long id) {
        return repository.findById(id);
    }

    public void deleteMedicalHistory(Long id) {
        repository.deleteById(id);
    }
}