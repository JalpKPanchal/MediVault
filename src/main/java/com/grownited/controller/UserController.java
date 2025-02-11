package com.grownited.controller;

import com.grownited.entity.UserEntity;
import com.grownited.entity.UserEntity.Status;
import com.grownited.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Inject PasswordEncoder

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("user", new UserEntity());
        return "Signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute UserEntity user, Model model) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encode password
        user.setStatus(Status.ACTIVE); // Default status
        userRepository.save(user);
        model.addAttribute("message", "Signup successful! Please login.");
        return "Login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "Login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();

            if (user.getStatus() == Status.DISABLED) {
                model.addAttribute("error", "Your account is disabled!");
                return "Login";
            }

            if (passwordEncoder.matches(password, user.getPassword())) { // Compare hashed password
                model.addAttribute("user", user);
                return "Dashboard"; // Redirect to dashboard (create this page)
            }
        }

        model.addAttribute("error", "Invalid email or password");
        return "Login";
    }
}
