package com.grownited.service;

import com.grownited.entity.OtpEntity;
import com.grownited.repository.OtpRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class OtpService {

    private final OtpRepository otpRepository;

    public OtpService(OtpRepository otpRepository) {
        this.otpRepository = otpRepository;
    }

    public void saveOtp(OtpEntity otpEntity) {
        otpRepository.save(otpEntity);
    }

    public Optional<OtpEntity> getOtpByEmail(String email) {
        return otpRepository.findByEmail(email);
    }

    public void deleteOtpByEmail(String email) {
        otpRepository.deleteByEmail(email);
    }
}