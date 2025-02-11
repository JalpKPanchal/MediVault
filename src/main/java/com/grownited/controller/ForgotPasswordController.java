package com.grownited.controller;

import com.grownited.entity.UserEntity;
import com.grownited.repository.UserRepository;
import com.grownited.service.EmailService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/password")
public class ForgotPasswordController {

    private final EmailService emailService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ForgotPasswordController(EmailService emailService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/forgot")
    public String forgotPasswordForm() {
        return "ForgotPassword";
    }

    @PostMapping("/send-otp")
    public String sendOtp(@RequestParam String email, Model model) {
        if (userRepository.findByEmail(email).isPresent()) {
            emailService.sendOtpEmail(email); // Generate and send OTP
            model.addAttribute("email", email);
            model.addAttribute("message", "OTP has been sent to your email.");
            return "VerifyOtp";
        }
        model.addAttribute("error", "Email not found! Please enter a valid email.");
        return "forgotPassword";
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestParam String email, @RequestParam String otp, Model model) {
        if (emailService.validateOtp(email, otp)) {
            model.addAttribute("email", email);
            return "ResetPassword";
        }
        model.addAttribute("error", "Invalid or expired OTP! Please request a new one.");
        return "VerifyOtp";
    }

    @PostMapping("/reset")
    public String resetPassword(@RequestParam String email, @RequestParam String newPassword, Model model) {
        Optional<UserEntity> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            UserEntity user = userOpt.get();
            user.setPassword(passwordEncoder.encode(newPassword)); // Encrypt password before saving
            userRepository.save(user);
            model.addAttribute("message", "Password changed successfully! You can now log in.");
            return "Login";
        }
        model.addAttribute("error", "User not found! Please try again.");
        return "ResetPassword";
    }
}
