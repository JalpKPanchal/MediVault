package com.grownited.controller;

import com.grownited.entity.AppointmentEntity;
import com.grownited.entity.AppointmentEntity.AppointmentStatus;
import com.grownited.entity.DoctorProfileEntity;
import com.grownited.entity.UserEntity;
import com.grownited.service.AppointmentService;
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
    private AppointmentService appointmentService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String listAppointments(Model model, HttpSession session) {
        UserEntity loggedInUser = (UserEntity) session.getAttribute("loggedInUser");
        List<AppointmentEntity> appointments;
        Map<UUID, String> patientNames = new HashMap<>();

        if (loggedInUser != null && loggedInUser.getRole() == UserEntity.Role.PATIENT) {
            appointments = appointmentService.getAppointmentsByPatientId(loggedInUser.getUserId());
        } else if (loggedInUser != null && loggedInUser.getRole() == UserEntity.Role.DOCTOR) {
            Optional<DoctorProfileEntity> doctorProfileOpt = doctorProfileService.getDoctorProfileByUserId(loggedInUser.getUserId());
            if (doctorProfileOpt.isPresent()) {
                appointments = appointmentService.getAppointmentsByDoctor(doctorProfileOpt.get());
            } else {
                appointments = List.of();
            }
        } else {
            return "redirect:/user/login?error=PleaseLoginToViewAppointments";
        }

        for (AppointmentEntity appointment : appointments) {
            Optional<UserEntity> patientOpt = userService.getUserById(appointment.getPatientId());
            patientNames.put(appointment.getPatientId(), patientOpt.map(user -> user.getFirstName() + " " + user.getLastName()).orElse("Unknown Patient"));
        }

        model.addAttribute("appointments", appointments);
        model.addAttribute("patientNames", patientNames);
        return "appointments";
    }

    @GetMapping("/book")
    public String bookAppointment(@RequestParam("doctorId") UUID doctorId, Model model) {
        Optional<UserEntity> doctorOpt = userService.getUserById(doctorId);
        if (doctorOpt.isPresent()) {
            Optional<DoctorProfileEntity> doctorProfileOpt = doctorProfileService.getDoctorProfileByUserId(doctorId);
            if (doctorProfileOpt.isPresent()) {
                model.addAttribute("doctor", doctorProfileOpt.get());
                return "BookAppointment";
            }
        }
        return "redirect:/doctorProfile/doctors?error=DoctorNotFound";
    }

    @PostMapping("/save")
    public String saveAppointment(
            @RequestParam("doctorId") UUID doctorId,
            @RequestParam("appointmentDate") String appointmentDate,
            @RequestParam("appointmentTime") String appointmentTime,
            @RequestParam(value = "description", required = false) String description,
            HttpSession session) {
        Optional<DoctorProfileEntity> doctorProfileOpt = doctorProfileService.getDoctorProfileByUserId(doctorId);
        if (!doctorProfileOpt.isPresent()) {
            return "redirect:/doctorProfile/doctors?error=DoctorNotFound";
        }

        AppointmentEntity appointment = new AppointmentEntity();
        appointment.setDoctor(doctorProfileOpt.get());

        UserEntity loggedInUser = (UserEntity) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            appointment.setPatientId(loggedInUser.getUserId());
        } else {
            return "redirect:/user/login?error=PleaseLoginToBook";
        }

        appointment.setAppointmentDate(LocalDate.parse(appointmentDate));
        appointment.setAppointmentTime(LocalTime.parse(appointmentTime));
        appointment.setStatus(AppointmentStatus.PENDING);
        appointment.setComment(description);

        appointmentService.saveAppointment(appointment);

        return "redirect:/appointments?success=true";
    }
}