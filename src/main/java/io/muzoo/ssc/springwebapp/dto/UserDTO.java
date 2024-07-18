package io.muzoo.ssc.springwebapp.dto;

import io.muzoo.ssc.springwebapp.models.Role;
import io.muzoo.ssc.springwebapp.models.User;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private String username, email,
            password, address, firstName,
            lastName, displayName,
            contact, phoneNumber, biography;

    private int age;
    private double height;
    private Set<String> preferences, dislikes;
    private List<String> matches;
    private Role role;

}

