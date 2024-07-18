package io.muzoo.ssc.springwebapp.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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