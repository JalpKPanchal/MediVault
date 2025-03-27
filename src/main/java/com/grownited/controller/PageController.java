package com.grownited.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/doctors")
    public String doctors() {
        return "doctors"; // Resolves to /WEB-INF/views/doctors.jsp
    }

//    @GetMapping("/appointments")
//    public String appointments() {
//        return "appointments"; // Resolves to /WEB-INF/views/appointments.jsp
//    }

    @GetMapping("/about")
    public String about() {
        return "about"; // Resolves to /WEB-INF/views/about.jsp
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact"; // Resolves to /WEB-INF/views/contact.jsp
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Resolves to /WEB-INF/views/login.jsp
    }

    @GetMapping("/register")
    public String register() {
        return "register"; // Resolves to /WEB-INF/views/register.jsp
    }

    @GetMapping("/privacy")
    public String privacy() {
        return "privacy"; // Resolves to /WEB-INF/views/privacy.jsp
    }
}