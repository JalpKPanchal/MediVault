package com.grownited.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "doctor_profiles")
public class DoctorProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer docProfileId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private String firstName;
    private String lastName;

    private String profilePic;

    // Getters and setters
    public Integer getDocProfileId() {
        return docProfileId;
    }

    public void setDocProfileId(Integer docProfileId) {
        this.docProfileId = docProfileId;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
}