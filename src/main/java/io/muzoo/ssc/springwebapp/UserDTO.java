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

    private String username, email, password, firstName, lastName, displayName, profilePicture, phoneNumber, biography;
    private int age;
    private double height;
    private Set<String> preferences, dislikes;

    public UserDTO(String username, String email, String password, String firstName, String lastName, int age, double height, String displayName, String profilePicture, String phoneNumber, String biography, Set<String> preferences, Set<String> dislikes) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.height = height;
        this.displayName = displayName;
        this.profilePicture = profilePicture;
        this.phoneNumber = phoneNumber;
        this.biography = biography;
        this.preferences = preferences;
        this.dislikes = dislikes;
    }

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.age = user.getAge();
        this.height = user.getHeight();
        this.displayName = user.getDisplayName();
        this.profilePicture = user.getProfilePicture();
        this.phoneNumber = user.getPhoneNumber();
        this.biography = user.getBiography();
        this.preferences = user.getPreferences();
        this.dislikes = user.getDislikes();
    }
}
