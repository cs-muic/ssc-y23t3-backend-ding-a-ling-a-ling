package io.muzoo.ssc.springwebapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    String firstName;
    String lastName;
    String email;
    String password;
    String username;
    String Address;
    String phoneNumber;
    int age;
    double height;
    String displayName;
    String profilePicture;
    String contact;
    String biography;;
    Set<String> dislikes;
    Set<String> preferences;

}