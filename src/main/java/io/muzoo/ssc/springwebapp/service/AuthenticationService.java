package io.muzoo.ssc.springwebapp.service;

import io.muzoo.ssc.springwebapp.dto.AuthenticationResponse;
import io.muzoo.ssc.springwebapp.dto.SignInRequest;
import io.muzoo.ssc.springwebapp.dto.SignUpRequest;
import io.muzoo.ssc.springwebapp.dto.UserDTO;
import io.muzoo.ssc.springwebapp.models.Role;
import io.muzoo.ssc.springwebapp.models.User;
import io.muzoo.ssc.springwebapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    public final UserRepository userRepository;
    final UserService userService;
    final PasswordEncoder passwordEncoder;
    final JwtService jwtService;
    final AuthenticationManager authenticationManager;
    final ImageService imageService;

    public AuthenticationResponse signup(SignUpRequest request) throws IOException {

        if (userRepository.existsByUsername(request.getUsername())) {
            System.out.println("username:" + request.getUsername());
            return AuthenticationResponse.builder().response("Username already taken").build();
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            return AuthenticationResponse.builder().response("Email already taken").build();
        }

        var user = UserDTO
                .builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .age(request.getAge())
                .height(request.getHeight())
                .displayName(request.getDisplayName())
                .contact(request.getContact())
                .biography(request.getBiography())
                .dislikes(request.getDislikes())
                .preferences(request.getPreferences())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        userService.createUser(user);

        String username = user.getUsername();

        MultipartFile profilePicture = request.getProfilePicture();
        var jwt = jwtService.generateToken(user.getUsername());

        if (!imageService.saveImageToStorage(username, profilePicture)){
            return AuthenticationResponse.builder().token(jwt).response("User created but failed to save image").build();
        }

        return AuthenticationResponse.builder().token(jwt).response("User successfully created!").build();
    }


    public AuthenticationResponse signin(SignInRequest request) {
        try {
            String password = request.getPassword();
            String username = request.getUsername();

            System.out.println("username:" + username);


            User user = userRepository.findByUsername(username).orElse(null);

            if (user == null) {
                return AuthenticationResponse.builder().response("Invalid username.").build();
            }

            if (!passwordEncoder.matches(password, user.getPassword())) {
                return AuthenticationResponse.builder().response("Wrong password.").build();
            }

            var jwt = jwtService.generateToken(user.getUsername());

            return AuthenticationResponse.builder().token(jwt).response("successfully changed").build();

        } catch (AuthenticationException e) {

            return AuthenticationResponse.builder().response("Invalid email or password.").build();
        }

    }


}