package com.grownited.controller;

import com.grownited.entity.DoctorProfileEntity;
import com.grownited.entity.UserEntity;
import com.grownited.service.CloudinaryService;
import com.grownited.service.DoctorProfileService;
import com.grownited.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/doctorProfile")
public class DoctorProfileController {

    @Autowired
    private DoctorProfileService doctorProfileService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired 
    private UserService userService;

    // Show all doctor profiles
    @GetMapping("/list")
    public String listDoctorProfiles(Model model) {
        List<DoctorProfileEntity> doctorProfiles = doctorProfileService.getAllDoctorProfiles();
        model.addAttribute("doctorProfiles", doctorProfiles);
        return "ListDoctorProfiles"; // ✅ JSP file for displaying doctor profiles
    }

    // Show form to add/edit a doctor profile
    @GetMapping("/form")
    public String doctorProfileForm(Model model, HttpSession session) {
        UserEntity loggedInUser = (UserEntity) session.getAttribute("loggedInUser");

        if (loggedInUser == null || loggedInUser.getRole() != UserEntity.Role.DOCTOR) {
            return "redirect:/user/login?error=Unauthorized"; // Redirect if not logged in or not a doctor
        }

        Optional<DoctorProfileEntity> existingProfile = doctorProfileService.getDoctorProfileByUserId(loggedInUser.getUserId());

        if (existingProfile.isPresent()) {
            model.addAttribute("doctorProfileEntity", existingProfile.get());
        } else {
            model.addAttribute("doctorProfileEntity", new DoctorProfileEntity());
        }

        return "DoctorProfileForm"; // ✅ JSP form
    }

    // Save doctor profile
    @PostMapping("/save")
    public String saveDoctorProfile(@ModelAttribute DoctorProfileEntity doctorProfile,
                                    @RequestParam("profileImage") MultipartFile profileImage,
                                    HttpSession session) throws IOException {

        UserEntity loggedInUser = (UserEntity) session.getAttribute("loggedInUser");

        if (loggedInUser == null || loggedInUser.getRole() != UserEntity.Role.DOCTOR) {
            return "redirect:/user/login?error=Unauthorized";
        }

        doctorProfile.setUser(loggedInUser);

        // Handle file upload
        if (!profileImage.isEmpty()) {
            String imageUrl = cloudinaryService.uploadFile(profileImage);
            doctorProfile.setProfilePic(imageUrl);
        }

        doctorProfileService.saveDoctorProfile(doctorProfile);
        return "redirect:/doctorProfile/list"; // Redirect to profile list page
    }

    // Delete doctor profile
    @GetMapping("/delete/{docProfileId}")
    public String deleteDoctorProfile(@PathVariable Integer docProfileId) {
        doctorProfileService.deleteDoctorProfile(docProfileId);
        return "redirect:/doctorProfile/list";
    }

    // View doctor profile details
    @GetMapping("/view/{docProfileId}")
    public String viewDoctorProfile(@PathVariable Integer docProfileId, Model model) {
        Optional<DoctorProfileEntity> doctorProfileOpt = doctorProfileService.getDoctorProfileById(docProfileId);

        if (doctorProfileOpt.isPresent()) {
            model.addAttribute("doctorProfile", doctorProfileOpt.get());
            return "ViewDoctorProfile"; // ✅ JSP file to display doctor details
        } else {
            return "redirect:/doctorProfile/list?error=ProfileNotFound"; // Redirect if profile not found
        }
    }
}
