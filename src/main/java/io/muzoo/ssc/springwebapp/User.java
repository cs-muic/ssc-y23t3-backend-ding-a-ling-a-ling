package io.muzoo.ssc.springwebapp;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor

@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Account Details
    @Column(unique = true)
    private String username;

    @Column(nullable = false, length = 150)
    private String email;

    @Column(nullable = false)
    private String password;

    // Personal Details
    @Column(name = "first_name")
    private String FirstName;

    @Column(name = "last_name")
    private String LastName;

    private int age;

    @Column(name = "height_in_cm")
    private double height;

    // Initial Dating Profile
    @Column(name = "display_name")
    private String displayName;

    @Column(name = "profile_picture_url")
    private String profilePicture;

    @Column(name = "contact_number")
    private String contact;

    @Column(length = 5000) // A longer field for a biography
    private String biography;

    @ElementCollection
    @Column(name = "preferences")
    private Set<String> preferences = new HashSet<>();

    @ElementCollection
    @Column(name = "dislikes")
    private Set<String> dislikes = new HashSet<>();

}
