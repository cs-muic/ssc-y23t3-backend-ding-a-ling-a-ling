package io.muzoo.ssc.springwebapp.dto;

import io.muzoo.ssc.springwebapp.models.User;
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
}
