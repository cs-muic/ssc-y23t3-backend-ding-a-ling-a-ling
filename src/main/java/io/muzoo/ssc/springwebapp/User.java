package io.muzoo.ssc.springwebapp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Account Details
    @Column(unique = true)
    private String username;

    @Column(nullable = false)
    private String email;

    private String address;

    @Column(nullable = false)
    private String password;

    // Personal Details
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String phoneNumber;

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
    @CollectionTable(name = "join_user_preferences", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "preference")
    private Set<String> preferences = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "join_user_dislikes", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "dislike")
    private Set<String> dislikes = new HashSet<>();

}
