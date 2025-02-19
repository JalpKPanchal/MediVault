package com.grownited.controller;

import com.grownited.entity.UserEntity;
import com.grownited.service.UserService;
import jakarta.servlet.http.HttpSession;
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

    @GetMapping("/")
    public String loginForm() {
        return "Login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String email, 
            @RequestParam String password, 
            Model model, 
            HttpSession session
    ) {
        Optional<UserEntity> optionalUser = userService.login(email, password);

        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            session.setAttribute("loggedInUser", user); // Store user in session
            return "redirect:/dashboard"; // Redirect to Dashboard
        } else {
            model.addAttribute("error", "Invalid email, password, or account disabled!");
            return "Login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Clear session
        return "redirect:/user/login?logout=true"; // Redirect to login page
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        UserEntity user = (UserEntity) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/user/login"; // Redirect if not logged in
        }

        model.addAttribute("user", user);
        return "Dashboard"; // Show Dashboard.jsp
    }
}
