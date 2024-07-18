package io.muzoo.ssc.springwebapp.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SignUpRequest {
    String firstName;
    String lastName;
    String email;
    String password;
    String username;
    String address;
    String phoneNumber;
    int age;
    double height;
    String displayName;
    MultipartFile profilePicture;
    String contact;
    String biography;;
    Set<String> dislikes;
    Set<String> preferences;
}