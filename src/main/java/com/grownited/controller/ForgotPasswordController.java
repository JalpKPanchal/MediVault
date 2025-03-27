package com.grownited.controller;

import com.grownited.entity.OtpEntity;
import com.grownited.entity.UserEntity;
import com.grownited.service.OtpService;
import com.grownited.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/forgotPassword")
public class ForgotPasswordController {

    private final UserService userService;
    private final OtpService otpService;
    private final PasswordEncoder passwordEncoder;

    public ForgotPasswordController(UserService userService, OtpService otpService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.otpService = otpService;
        this.passwordEncoder = passwordEncoder;
    }

    // Show the forgot password form
    @GetMapping
    public String showForgotPasswordForm() {
        return "ForgotPasswordForm";
    }

    // Handle email submission to send OTP
    @PostMapping("/sendOtp")
    public String sendOtp(@RequestParam("email") String email, Model model) {
        Optional<UserEntity> userOptional = userService.getUserByEmail(email);
        if (userOptional.isEmpty()) {
            model.addAttribute("error", "Email not found");
            return "ForgotPasswordForm";
        }

        // Generate OTP and save it
        String otp = String.valueOf((int) (Math.random() * 900000) + 100000); // 6-digit OTP
        OtpEntity otpEntity = new OtpEntity();
        otpEntity.setId(UUID.randomUUID());
        otpEntity.setEmail(email);
        otpEntity.setOtp(otp);
        otpEntity.setExpiryTime(LocalDateTime.now().plusMinutes(10)); // OTP valid for 10 minutes
        otpService.saveOtp(otpEntity);

        // In a real application, send the OTP via email (e.g., using JavaMailSender)
        System.out.println("OTP for " + email + ": " + otp); // For testing purposes

        model.addAttribute("email", email);
        return "VerifyOtpForm";
    }

    // Verify OTP and show reset password form
    @PostMapping("/verifyOtp")
    public String verifyOtp(@RequestParam("email") String email, @RequestParam("otp") String otp, Model model) {
        Optional<OtpEntity> otpEntityOptional = otpService.getOtpByEmail(email);
        if (otpEntityOptional.isEmpty() || !otpEntityOptional.get().getOtp().equals(otp) || otpEntityOptional.get().getExpiryTime().isBefore(LocalDateTime.now())) {
            model.addAttribute("error", "Invalid or expired OTP");
            model.addAttribute("email", email);
            return "VerifyOtpForm";
        }

        // OTP is valid, show reset password form
        model.addAttribute("email", email);
        return "ResetPasswordForm";
    }

    // Reset password
    @PostMapping("/reset")
    public String resetPassword(@RequestParam("email") String email, @RequestParam("newPassword") String newPassword, Model model) {
        Optional<UserEntity> userOptional = userService.getUserByEmail(email);
        if (userOptional.isEmpty()) {
            model.addAttribute("error", "User not found");
            return "ResetPasswordForm";
        }

        UserEntity user = userOptional.get();
        user.setPassword(passwordEncoder.encode(newPassword)); // Encode the new password
        userService.saveUser(user);

        // Delete the OTP after successful password reset
        otpService.deleteOtpByEmail(email);

        model.addAttribute("success", "Password reset successfully. Please log in.");
        return "Login";
    }
}