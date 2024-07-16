package io.muzoo.ssc.springwebapp.service;

import io.muzoo.ssc.springwebapp.dto.UpdateUserRequest;
import io.muzoo.ssc.springwebapp.dto.UserDTO;
import io.muzoo.ssc.springwebapp.models.User;
import io.muzoo.ssc.springwebapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.jaxb.cfg.spi.JaxbCfgEventListenerGroupType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;
    final JwtService jwtService;
    private final ImageService imageService;

    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User search(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public String getProfile(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (user != null) {
            StringBuilder profiles = new StringBuilder();
            profiles.append("Username: ").append(user.getUsername()).append("\n");
            profiles.append("Email: ").append(user.getEmail()).append("\n");
            profiles.append("Address: ").append(user.getAddress()).append("\n");
            profiles.append("First Name: ").append(user.getFirstName()).append("\n");
            profiles.append("Last Name: ").append(user.getLastName()).append("\n");
            profiles.append("Phone Number: ").append(user.getPhoneNumber()).append("\n");
            profiles.append("Age: ").append(user.getAge()).append("\n");
            profiles.append("Height: ").append(user.getHeight()).append("\n");
            profiles.append("Display Name: ").append(user.getDisplayName()).append("\n");
            profiles.append("Contact: ").append(user.getContact()).append("\n");
            profiles.append("Biography: ").append(user.getBiography()).append("\n");
            profiles.append("Preferences: ").append(user.getPreferences()).append("\n");
            profiles.append("Dislikes: ").append(user.getDislikes()).append("\n");
            return profiles.toString();
        }
        return null;
    }

    public String createUser(UserDTO userDTO) {
        User user = new User();
        setUserInfo(user, userDTO);
        userRepository.save(user);
        return String.format("Created user %s successfully", userDTO.getUsername());
    }

    public String convertTokenToUsername(String token) {

        String username = jwtService.extractUsername(token);
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (!jwtService.validateToken(token, user)) {
            return "invalid";
        }

        return username;
    }

    public String updateUser(UpdateUserRequest updateUserRequest) throws IOException {
        String token = updateUserRequest.getToken();
        if (updateUserRequest.getToken() == null) {
            return "Token is required";
        }

        String username = convertTokenToUsername(token);
        if (username.equalsIgnoreCase("invalid")) {
            return "Token is invalid";
        }

        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // change user info just the one that isn't black or null
        String firstName = updateUserRequest.getFirstName();
        String lastName = updateUserRequest.getLastName();
        String address = updateUserRequest.getAddress();
        String phoneNumber = updateUserRequest.getPhoneNumber();
        int age = updateUserRequest.getAge();
        double height = updateUserRequest.getHeight();
        String displayName = updateUserRequest.getDisplayName();
        String contact = updateUserRequest.getContact();
        String biography = updateUserRequest.getBiography();
        Set<String> dislikes = updateUserRequest.getDislikes();
        Set<String> preferences = updateUserRequest.getPreferences();
        MultipartFile profileUser = updateUserRequest.getProfilePicture();

        if (firstName != null && !firstName.isBlank()) {
            user.setFirstName(firstName);
        }
        if (lastName != null && !lastName.isBlank()) {
            user.setLastName(lastName);
        }
        if (address != null && !address.isBlank()) {
            user.setAddress(address);
        }
        if (phoneNumber != null && !phoneNumber.isBlank()) {
            user.setPhoneNumber(phoneNumber);
        }
        if (age != 0) {
            user.setAge(age);
        }
        if (height != 0) {
            user.setHeight(height);
        }
        if (displayName != null && !displayName.isBlank()) {
            user.setDisplayName(displayName);
        }
        if (contact != null && !contact.isBlank()) {
            user.setContact(contact);
        }
        if (biography != null && !biography.isBlank()) {
            user.setBiography(biography);
        }
        if (dislikes != null && !dislikes.isEmpty()) {
            user.setDislikes(dislikes);
        }
        if (preferences != null && !preferences.isEmpty()) {
            user.setPreferences(preferences);
        }

        if (profileUser != null) {
            imageService.saveImageToStorage(username, profileUser);
        }

        userRepository.save(user);
        return String.format("Updated user %s successfully", user.getUsername());
    }

    private void setUserInfo(User user, UserDTO userDTO) {

        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setAddress(userDTO.getAddress());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setPassword(userDTO.getPassword());
        user.setAge(userDTO.getAge());
        user.setHeight(userDTO.getHeight());
        user.setDisplayName(userDTO.getDisplayName());
        user.setContact(userDTO.getContact());
        user.setBiography(userDTO.getBiography());
        user.setPreferences(userDTO.getPreferences());
        user.setDislikes(userDTO.getDislikes());
    }


    public String getAllUsers() {
        StringBuilder allUsers = new StringBuilder();
        for (User user : userRepository.findAll()) {
            allUsers.append(user.getUsername()).append("\n");
        }
        return allUsers.toString();
    }

    public List<User> findMatchesByToken(String token) {
        String username = convertTokenToUsername(token);

//        UserDTO user = userRepository.findByUsername(username).isEmpty() ? null : userRepository.findByUsername(username).get();

        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (user == null) {
            throw new RuntimeException("User not found with username: " + username);
        }

        List<User> users = userRepository.findUsersByDislikesAndPreferences(user.getDislikes(), user.getPreferences());

        return userRepository.findUsersByDislikesAndPreferences(user.getDislikes(), user.getPreferences());
    }

    public List<String> getUserDislikes(String token){
        String username = convertTokenToUsername(token);
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        ArrayList<String> dislikesList = new ArrayList<>(user.getDislikes());
        return dislikesList;
    }

    public List<String> getUserPreferences(String token){
        String username = convertTokenToUsername(token);
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        ArrayList<String> preferencesList = new ArrayList<>(user.getPreferences());
        return preferencesList;
    }

}
