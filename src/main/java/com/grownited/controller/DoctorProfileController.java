package com.grownited.controller;

import com.grownited.entity.DoctorProfileEntity;
import com.grownited.service.DoctorProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/doctorProfile")
public class DoctorProfileController {

    private static String UPLOAD_DIR = "uploads/";

    @Autowired
    private DoctorProfileService doctorProfileService;

    // Show all doctor profiles
    @GetMapping("/list")
    public String listDoctorProfiles(Model model) {
        List<DoctorProfileEntity> doctorProfiles = doctorProfileService.getAllDoctorProfiles();
        model.addAttribute("doctorProfiles", doctorProfiles);
        return "List";
    }

    // Show form to add a new doctor profile
    @GetMapping("/new")
    public String newDoctorProfileForm(Model model) {
        model.addAttribute("doctorProfileEntity", new DoctorProfileEntity());
        return "Form";
    }

    // Create or update doctor profile
    @PostMapping("/save")
    public String saveDoctorProfile(@ModelAttribute DoctorProfileEntity doctorProfileEntity, 
                                    @RequestParam("profilePic") MultipartFile profilePic) throws IOException {
        
        // Handle file upload
        if (!profilePic.isEmpty()) {
            // Ensure the 'uploads' directory exists
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs(); // Create directory if it does not exist
            }

            // Generate unique filename
            String filename = System.currentTimeMillis() + "_" + profilePic.getOriginalFilename();

            // Path to save the file
            Path path = Paths.get(UPLOAD_DIR + filename);
            Files.write(path, profilePic.getBytes());  // Save the file
            
            // Store the filename (not the file object) in the entity
            doctorProfileEntity.setProfilePic(filename);
        } else {
            // If no file was uploaded, handle it (set a default value or null)
            doctorProfileEntity.setProfilePic(null);
        }

        // Save or update the doctor profile
        doctorProfileService.saveDoctorProfile(doctorProfileEntity);
        return "redirect:/doctorProfile/list";
    }


    // Show form to update doctor profile
    @GetMapping("/edit/{docProfileId}")
    public String editDoctorProfile(@PathVariable Integer docProfileId, Model model) {
        Optional<DoctorProfileEntity> doctorProfile = doctorProfileService.getDoctorProfileById(docProfileId);
        if (doctorProfile.isPresent()) {
            model.addAttribute("doctorProfileEntity", doctorProfile.get());
            return "Form";
        }
        return "redirect:/doctorProfile/list";
    }

    // Delete doctor profile
    @GetMapping("/delete/{docProfileId}")
    public String deleteDoctorProfile(@PathVariable Integer docProfileId) {
        doctorProfileService.deleteDoctorProfile(docProfileId);
        return "redirect:/doctorProfile/list";
    }
}
