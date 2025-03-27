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

    @GetMapping("/list")
    public String listDoctorProfiles(Model model) {
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

        if (existingProfile != null) {
            model.addAttribute("doctorProfileEntity", existingProfile);
        } else {
            DoctorProfileEntity newProfile = new DoctorProfileEntity();
            newProfile.setUser(loggedInUser);
            model.addAttribute("doctorProfileEntity", newProfile);
        }

        return "DoctorProfileForm";
    }

    @PostMapping("/save")
    public String saveDoctorProfile(@ModelAttribute DoctorProfileEntity doctorProfile,
                                    @RequestParam("profileImage") MultipartFile profileImage,
                                    HttpSession session) throws IOException {

        UserEntity loggedInUser = (UserEntity) session.getAttribute("loggedInUser");

        if (loggedInUser == null || loggedInUser.getRole() != UserEntity.Role.DOCTOR) {
            return "redirect:/user/login?error=Unauthorized";
        }

        doctorProfile.setUser(loggedInUser);

        if (!profileImage.isEmpty()) {
            String imageUrl = cloudinaryService.uploadFile(profileImage);
            doctorProfile.setProfilePic(imageUrl);
        }

        doctorProfileService.saveDoctorProfile(doctorProfile);
        return "redirect:/dashboard";
    }

    @GetMapping("/delete/{docProfileId}")
    public String deleteDoctorProfile(@PathVariable Integer docProfileId) {
        doctorProfileService.deleteDoctorProfile(docProfileId);
        return "redirect:/doctorProfile/list";
    }

    @GetMapping("/view/{docProfileId}")
    public String viewDoctorProfile(@PathVariable Integer docProfileId, Model model) {
        Optional<DoctorProfileEntity> doctorProfileOpt = doctorProfileService.getDoctorProfileById(docProfileId);

        if (doctorProfileOpt.isPresent()) {
            model.addAttribute("doctorProfile", doctorProfileOpt.get());
            return "ViewDoctorProfile";
        } else {
            return "redirect:/doctorProfile/list?error=ProfileNotFound";
        }
    }
    
    @GetMapping("/doctors")
    public String listDoctors(Model model) {
        List<DoctorProfileEntity> doctorProfiles = doctorProfileService.getAllDoctorProfiles();
        model.addAttribute("doctorProfiles", doctorProfiles);
        return "doctors";
    }
}