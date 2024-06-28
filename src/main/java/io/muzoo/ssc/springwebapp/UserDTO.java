package io.muzoo.ssc.springwebapp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

// POJO
// Plain Old Java Object
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserDTO {

    private String username, email,
            password, address, firstName,
            lastName, displayName, profilePicture,
            contact, phoneNumber, biography;
    private int age;
    private double height;
    private Set<String> preferences, dislikes;

    UserDTO(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.address = user.getAddress();
        this.password = user.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.phoneNumber = user.getPhoneNumber();
        this.age = user.getAge();
        this.height = user.getHeight();
        this.displayName = user.getDisplayName();
        this.profilePicture = user.getProfilePicture();
        this.contact = user.getContact();
        this.biography = user.getBiography();
        this.preferences = user.getPreferences();
        this.dislikes = user.getDislikes();
    }
}
