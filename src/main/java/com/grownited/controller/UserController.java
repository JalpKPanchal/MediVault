package com.grownited.controller;

import com.grownited.entity.UserEntity;
import com.grownited.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "Login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        UserEntity user = userService.authenticate(email, password);
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/user/dashboard";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "Login";
        }
    }

    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        model.addAttribute("user", user);
        return "Dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/user/login";
    }

    @GetMapping("/forgotPassword")
    public String showForgotPasswordForm() {
        return "ForgotPassword";
    }

    @PostMapping("/forgotPassword")
    public String processForgotPassword(@RequestParam String email, Model model) {
        // Placeholder for password reset logic (e.g., send OTP via email)
        model.addAttribute("message", "If an account with that email exists, a password reset link has been sent.");
        return "ForgotPassword";
    }
}