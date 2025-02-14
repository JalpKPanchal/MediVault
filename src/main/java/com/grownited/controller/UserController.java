package com.grownited.controller;

import com.grownited.entity.UserEntity;
import com.grownited.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("user", new UserEntity());
        return "Signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute UserEntity user, Model model) {
        userService.signup(user);
        model.addAttribute("message", "Signup successful! Please login.");
        return "Login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "Login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        Optional<UserEntity> optionalUser = userService.login(email, password);

        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            model.addAttribute("user", user);
            return "Dashboard"; // Create Dashboard.jsp as a landing page
        } else {
            model.addAttribute("error", "Invalid email, password, or account disabled!");
            return "Login";
        }
    }
}
