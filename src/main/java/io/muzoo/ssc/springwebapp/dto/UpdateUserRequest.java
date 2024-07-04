package io.muzoo.ssc.springwebapp.dto;

import lombok.*;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateUserRequest {
    String token;
    String firstName;
    String lastName;
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