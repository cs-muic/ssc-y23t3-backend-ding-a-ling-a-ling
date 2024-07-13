package io.muzoo.ssc.springwebapp.service;

import io.muzoo.ssc.springwebapp.dto.AuthenticationResponse;
import io.muzoo.ssc.springwebapp.dto.SignInRequest;
import io.muzoo.ssc.springwebapp.dto.SignUpRequest;
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

    @Autowired
    final UserService userService;

    final PasswordEncoder passwordEncoder;
    final JwtService jwtService;
    final AuthenticationManager authenticationManager;
    final ImageService imageService;

    public AuthenticationResponse signup(SignUpRequest request) throws IOException {

        if (userRepository.existsByUsername(request.getUsername())) {
            return AuthenticationResponse.builder().response("Username already taken").build();
        }

        var user = User
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

        user = userService.save(user);

        String username = user.getUsername();

        MultipartFile profilePicture = request.getProfilePicture();
        if (!imageService.saveImageToStorage(username, profilePicture)){
            return AuthenticationResponse.builder().response("User created but failed to save image").build();
        }

        var jwt = jwtService.generateToken(user.getUsername());
        return AuthenticationResponse.builder().token(jwt).response("User successfully created!").build();
    }


    public AuthenticationResponse signin(SignInRequest request) {
        try {
            // Authenticating the user's credentials

            String password = request.getPassword();
            String email = request.getEmail();

            var user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));


            if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new IllegalArgumentException("Invalid email or password.");
            }

            var jwt = jwtService.generateToken(user.getUsername());

            return AuthenticationResponse.builder().token(jwt).build();
        } catch (AuthenticationException e) {
            return AuthenticationResponse.builder().response("Invalid email or password.").build();
        }
    }


}