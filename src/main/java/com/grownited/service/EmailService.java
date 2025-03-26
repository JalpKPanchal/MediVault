package com.grownited.service;

import com.grownited.entity.AppointmentEntity;
import com.grownited.entity.OtpEntity;
import com.grownited.repository.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private OtpRepository otpRepository;

    private String generateOTP() {
        return String.valueOf(100000 + new Random().nextInt(900000));
    }

    public String sendOtpEmail(String email) {
        String otp = generateOTP();
        OtpEntity otpEntity = new OtpEntity();
        otpEntity.setEmail(email);
        otpEntity.setOtp(otp);
        otpEntity.setExpiryTime(LocalDateTime.now().plusMinutes(5));
        otpRepository.save(otpEntity);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("MediVault OTP for Password Reset");
        message.setText("Your OTP for password reset is: " + otp + ". It is valid for 5 minutes.");
        mailSender.send(message);
        return otp;
    }

    public boolean validateOtp(String email, String otp) {
        Optional<OtpEntity> optionalOtp = otpRepository.findByEmail(email);
        if (optionalOtp.isPresent()) {
            OtpEntity savedOtp = optionalOtp.get();
            if (savedOtp.getOtp().equals(otp) && LocalDateTime.now().isBefore(savedOtp.getExpiryTime())) {
                otpRepository.delete(savedOtp);
                return true;
            }
        }
        return false;
    }

    public void sendAppointmentConfirmation(AppointmentEntity appointment) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(appointment.getDoctor().getUser().getEmail());
        message.setSubject("MediVault Appointment Confirmation");
        message.setText("A new appointment has been booked:\n" +
                "Patient: " + appointment.getPatientId() + "\n" +
                "Date: " + appointment.getAppointmentDate() + "\n" +
                "Time: " + appointment.getAppointmentTime() + "\n" +
                "Status: " + appointment.getStatus());
        mailSender.send(message);
    }

    public void sendAppointmentStatusUpdate(AppointmentEntity appointment) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(appointment.getDoctor().getUser().getEmail());
        message.setSubject("MediVault Appointment Status Update");
        message.setText("Appointment status updated:\n" +
                "Patient: " + appointment.getPatientId() + "\n" +
                "Date: " + appointment.getAppointmentDate() + "\n" +
                "Time: " + appointment.getAppointmentTime() + "\n" +
                "New Status: " + appointment.getStatus());
        mailSender.send(message);
    }
}