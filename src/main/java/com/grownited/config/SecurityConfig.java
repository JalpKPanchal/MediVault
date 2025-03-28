package com.grownited.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // Allow public access to the root path, login page, forgot password, and static resources
                .requestMatchers("/", "/user/login", "/user/forgotPassword", "/resources/**", "/static/**", "/css/**", "/js/**", "/images/**").permitAll()
                // Require authentication for all other requests
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                // Specify the custom login page
                .loginPage("/user/login")
                // Redirect to a dashboard after successful login (adjust as needed)
                .defaultSuccessUrl("/dashboard", true)
                // Allow everyone to access the login page
                .permitAll()
            )
            .logout(logout -> logout
                // Define the logout URL
                .logoutUrl("/logout")
                // Redirect to the login page after logout
                .logoutSuccessUrl("/user/login?logout")
                .permitAll()
            );

        return http.build();
    }
}