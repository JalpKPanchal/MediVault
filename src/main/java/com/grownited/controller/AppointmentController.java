package com.grownited.controller;

import com.grownited.entity.AppointmentEntity;
import com.grownited.entity.DoctorProfileEntity;
import com.grownited.entity.UserEntity;
import com.grownited.service.AppointmentService;
import com.grownited.service.DoctorProfileService;
import com.grownited.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final DoctorProfileService doctorProfileService;
    private final UserService userService;

    public AppointmentController(AppointmentService appointmentService, DoctorProfileService doctorProfileService, UserService userService) {
        this.appointmentService = appointmentService;
        this.doctorProfileService = doctorProfileService;
        this.userService = userService;
    }

    @GetMapping
    public String listAppointments(HttpSession session, Model model) {
        UserEntity loggedInUser = (UserEntity) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            return "redirect:/user/login?error=Please login to view appointments";
        }

        List<AppointmentEntity> appointments;
        Map<Long, String> patientNames = new HashMap<>();

        if (loggedInUser.getRole() == UserEntity.Role.PATIENT) {
            appointments = appointmentService.getAppointmentsByPatientId(loggedInUser.getUserId());
            for (AppointmentEntity appointment : appointments) {
                patientNames.put(appointment.getPatientId(), loggedInUser.getFirstName() + " " + loggedInUser.getLastName());
            }
        } else if (loggedInUser.getRole() == UserEntity.Role.DOCTOR) {
            appointments = appointmentService.getAppointmentsByDoctorId(loggedInUser.getUserId());
            for (AppointmentEntity appointment : appointments) {
                Optional<UserEntity> patient = userService.getUserById(appointment.getPatientId());
                patientNames.put(appointment.getPatientId(), patient.map(user -> user.getFirstName() + " " + user.getLastName()).orElse("Unknown"));
            }
        } else {
            return "redirect:/dashboard?error=Unauthorized";
        }

        model.addAttribute("appointments", appointments);
        model.addAttribute("patientNames", patientNames);
        return "appointments";
    }

    @GetMapping("/book")
    public String bookAppointment(@RequestParam("doctorId") Long doctorId, HttpSession session, Model model) {
        UserEntity loggedInUser = (UserEntity) session.getAttribute("loggedInUser");

        if (loggedInUser == null || loggedInUser.getRole() != UserEntity.Role.PATIENT) {
            return "redirect:/user/login?error=Please login as a patient to book an appointment";
        }

        Optional<DoctorProfileEntity> doctorProfile = doctorProfileService.getDoctorProfileByUserId(doctorId);
        if (doctorProfile.isEmpty()) {
            return "redirect:/doctorProfile/doctors?error=Doctor not found";
        }

        model.addAttribute("doctor", doctorProfile.get());
        return "BookAppointment";
    }

    @PostMapping("/save")
    public String saveAppointment(
            @RequestParam("doctorId") Long doctorId,
            @RequestParam("appointmentDate") String appointmentDate,
            @RequestParam("appointmentTime") String appointmentTime,
            @RequestParam(value = "description", required = false) String description,
            HttpSession session) {
        UserEntity loggedInUser = (UserEntity) session.getAttribute("loggedInUser");

        if (loggedInUser == null || loggedInUser.getRole() != UserEntity.Role.PATIENT) {
            return "redirect:/user/login?error=Please login as a patient to book an appointment";
        }

        Optional<DoctorProfileEntity> doctorProfile = doctorProfileService.getDoctorProfileByUserId(doctorId);
        if (doctorProfile.isEmpty()) {
            return "redirect:/doctorProfile/doctors?error=Doctor not found";
        }

        AppointmentEntity appointment = new AppointmentEntity();
        appointment.setPatientId(loggedInUser.getUserId());
        appointment.setDoctor(doctorProfile.get());
        appointment.setAppointmentDate(LocalDate.parse(appointmentDate));
        appointment.setAppointmentTime(LocalTime.parse(appointmentTime));
        appointment.setDescription(description);
        appointment.setStatus(AppointmentEntity.Status.PENDING);

        appointmentService.saveAppointment(appointment);
        return "redirect:/appointments?success=Appointment booked successfully";
    }
}