package com.grownited.controller;

import com.grownited.entity.AppointmentEntity;
import com.grownited.entity.AppointmentEntity.AppointmentStatus;
import com.grownited.entity.DoctorProfileEntity;
import com.grownited.entity.UserEntity;
import com.grownited.repository.AppointmentRepository;
import com.grownited.service.DoctorProfileService;
import com.grownited.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.UUID;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private DoctorProfileService doctorProfileService;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/book")
    public String bookAppointment(@RequestParam("doctorId") Integer doctorId, Model model) {
        Optional<DoctorProfileEntity> doctorProfileOpt = doctorProfileService.getDoctorProfileById(doctorId);
        if (doctorProfileOpt.isPresent()) {
            model.addAttribute("doctor", doctorProfileOpt.get());
            return "BookAppointment";
        } else {
            return "redirect:/doctorProfile/doctors?error=DoctorNotFound";
        }
    }

    @PostMapping("/save")
    public String saveAppointment(
            @RequestParam("doctorId") Integer doctorId,
            @RequestParam("appointmentDate") String appointmentDate,
            @RequestParam("appointmentTime") String appointmentTime,
            @RequestParam(value = "description", required = false) String description,
            HttpSession session) {
        // Fetch the doctor
        Optional<DoctorProfileEntity> doctorProfileOpt = doctorProfileService.getDoctorProfileById(doctorId);
        if (!doctorProfileOpt.isPresent()) {
            return "redirect:/doctorProfile/doctors?error=DoctorNotFound";
        }

        // Create a new appointment
        AppointmentEntity appointment = new AppointmentEntity();
        appointment.setDoctor(doctorProfileOpt.get());

        // Set patientId from logged-in user (if available)
        UserEntity loggedInUser = (UserEntity) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            appointment.setPatientId(loggedInUser.getUserId());
        } else {
            // If no user is logged in, redirect to login
            return "redirect:/user/login?error=PleaseLoginToBook";
        }

        // Parse date and time
        appointment.setAppointmentDate(LocalDate.parse(appointmentDate));
        appointment.setAppointmentTime(LocalTime.parse(appointmentTime));

        // Set other fields
        appointment.setStatus(AppointmentStatus.PENDING);
        appointment.setComment(description);

        // Save the appointment
        appointmentRepository.save(appointment);

        return "redirect:/appointments?success=true";
    }

    @GetMapping
    public String listAppointments(Model model, HttpSession session) {
        UserEntity loggedInUser = (UserEntity) session.getAttribute("loggedInUser");
        List<AppointmentEntity> appointments;
        Map<UUID, String> patientNames = new HashMap<>();

        if (loggedInUser != null && loggedInUser.getRole() == UserEntity.Role.PATIENT) {
            // Show appointments for the logged-in patient
            appointments = appointmentRepository.findAll().stream()
                    .filter(appointment -> appointment.getPatientId().equals(loggedInUser.getUserId()))
                    .toList();
        } else if (loggedInUser != null && loggedInUser.getRole() == UserEntity.Role.DOCTOR) {
            // Show appointments for the logged-in doctor
            appointments = appointmentRepository.findAll().stream()
                    .filter(appointment -> appointment.getDoctor().getUser().getUserId().equals(loggedInUser.getUserId()))
                    .toList();
        } else {
            // If not logged in or not a patient/doctor, redirect to login
            return "redirect:/user/login?error=PleaseLoginToViewAppointments";
        }

        // Fetch patient names for display
        for (AppointmentEntity appointment : appointments) {
            Optional<UserEntity> patientOpt = userService.getUserById(appointment.getPatientId());
            patientNames.put(appointment.getPatientId(), patientOpt.map(user -> user.getFirstName() + " " + user.getLastName()).orElse("Unknown Patient"));
        }

        model.addAttribute("appointments", appointments);
        model.addAttribute("patientNames", patientNames);
        return "appointments";
    }
}