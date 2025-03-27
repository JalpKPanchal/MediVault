package com.grownited.controller;

import com.grownited.entity.DoctorProfileEntity;
import com.grownited.entity.UserEntity;
import com.grownited.service.CloudinaryService;
import com.grownited.service.DoctorProfileService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/doctorProfile")
public class DoctorProfileController {

    private final DoctorProfileService doctorProfileService;
    private final CloudinaryService cloudinaryService;

    public DoctorProfileController(DoctorProfileService doctorProfileService, CloudinaryService cloudinaryService) {
        this.doctorProfileService = doctorProfileService;
        this.cloudinaryService = cloudinaryService;
    }

    @GetMapping("/list")
    public String listDoctorProfiles(Model model, HttpSession session) {
        UserEntity loggedInUser = (UserEntity) session.getAttribute("loggedInUser");

        if (loggedInUser == null || loggedInUser.getRole() != UserEntity.Role.ADMIN) {
            return "redirect:/user/login?error=Unauthorized";
        }

        List<DoctorProfileEntity> doctorProfiles = doctorProfileService.getAllDoctorProfiles();
        model.addAttribute("doctorProfiles", doctorProfiles);
        return "ListDoctorProfiles";
    }

    @GetMapping("/form")
    public String doctorProfileForm(Model model, HttpSession session) {
        UserEntity loggedInUser = (UserEntity) session.getAttribute("loggedInUser");

        if (loggedInUser == null || loggedInUser.getRole() != UserEntity.Role.DOCTOR) {
            return "redirect:/user/login?error=Unauthorized";
        }

        Optional<DoctorProfileEntity> existingProfile = doctorProfileService.getDoctorProfileByUserId(loggedInUser.getUserId());

        if (existingProfile.isPresent()) {
            model.addAttribute("doctorProfileEntity", existingProfile.get());
        } else {
            DoctorProfileEntity newProfile = new DoctorProfileEntity();
            newProfile.setUser(loggedInUser);
            model.addAttribute("doctorProfileEntity", newProfile);
        }

        return "DoctorProfileForm";
    }

    @PostMapping("/save")
    public String saveDoctorProfile(
            @RequestParam("docProfileId") Long docProfileId,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam(value = "profileImage", required = false) MultipartFile profileImage,
            HttpSession session) {
        UserEntity loggedInUser = (UserEntity) session.getAttribute("loggedInUser");

        if (loggedInUser == null || loggedInUser.getRole() != UserEntity.Role.DOCTOR) {
            return "redirect:/user/login?error=Unauthorized";
        }

        DoctorProfileEntity doctorProfile;
        if (docProfileId != null && docProfileId > 0) {
            Optional<DoctorProfileEntity> existingProfile = doctorProfileService.getDoctorProfileById(docProfileId);
            if (existingProfile.isEmpty()) {
                return "redirect:/doctorProfile/form?error=Profile not found";
            }
            doctorProfile = existingProfile.get();
        } else {
            doctorProfile = new DoctorProfileEntity();
            doctorProfile.setUser(loggedInUser);
        }

        doctorProfile.setFirstName(firstName);
        doctorProfile.setLastName(lastName);

        if (profileImage != null && !profileImage.isEmpty()) {
            try {
                String imageUrl = cloudinaryService.uploadImage(profileImage);
                doctorProfile.setProfilePic(imageUrl);
            } catch (Exception e) {
                return "redirect:/doctorProfile/form?error=Failed to upload image";
            }
        }

        doctorProfileService.saveDoctorProfile(doctorProfile);
        return "redirect:/dashboard?success=Profile saved successfully";
    }

    @GetMapping("/view/{docProfileId}")
    public String viewDoctorProfile(@PathVariable("docProfileId") Long docProfileId, Model model, HttpSession session) {
        UserEntity loggedInUser = (UserEntity) session.getAttribute("loggedInUser");

        if (loggedInUser == null || loggedInUser.getRole() != UserEntity.Role.ADMIN) {
            return "redirect:/user/login?error=Unauthorized";
        }

        Optional<DoctorProfileEntity> doctorProfile = doctorProfileService.getDoctorProfileById(docProfileId);
        if (doctorProfile.isEmpty()) {
            return "redirect:/doctorProfile/list?error=Profile not found";
        }

        model.addAttribute("doctorProfile", doctorProfile.get());
        return "ViewDoctorProfile";
    }

    @GetMapping("/delete/{docProfileId}")
    public String deleteDoctorProfile(@PathVariable("docProfileId") Long docProfileId, HttpSession session) {
        UserEntity loggedInUser = (UserEntity) session.getAttribute("loggedInUser");

        if (loggedInUser == null || loggedInUser.getRole() != UserEntity.Role.ADMIN) {
            return "redirect:/user/login?error=Unauthorized";
        }

        doctorProfileService.deleteDoctorProfile(docProfileId);
        return "redirect:/doctorProfile/list?success=Profile deleted successfully";
    }

    @GetMapping("/doctors")
    public String listDoctors(Model model) {
        List<DoctorProfileEntity> doctorProfiles = doctorProfileService.getAllDoctorProfiles();
        model.addAttribute("doctorProfiles", doctorProfiles);
        return "doctors";
    }
}