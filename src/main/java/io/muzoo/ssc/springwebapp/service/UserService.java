package io.muzoo.ssc.springwebapp.service;

import io.muzoo.ssc.springwebapp.SpringWebappApplication;
import io.muzoo.ssc.springwebapp.dto.UpdateUserRequest;
import io.muzoo.ssc.springwebapp.dto.UserDTO;
import io.muzoo.ssc.springwebapp.models.User;
import io.muzoo.ssc.springwebapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.jaxb.cfg.spi.JaxbCfgEventListenerGroupType;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;
    final JwtService jwtService;
    private final ImageService imageService;
    private final SpringWebappApplication springWebappApplication;
    private final DataSourceTransactionManagerAutoConfiguration dataSourceTransactionManagerAutoConfiguration;

    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public UserDTO getProfile(String username) throws IOException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        UserDTO userDTO = UserDTO.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .address(user.getAddress())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .age(user.getAge())
                .height(user.getHeight())
                .displayName(user.getDisplayName())
                .contact(user.getContact())
                .biography(user.getBiography())
                .dislikes(user.getDislikes())
                .preferences(user.getPreferences())
                .matches(new ArrayList<>())
                .build();
        return userDTO;
    }

    public String convertTokenToUsername(String token) {
        String username = jwtService.extractUsername(token);
        if (username == null) {
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

    public String getAllUsers() {
        StringBuilder allUsers = new StringBuilder();
        for (User user : userRepository.findAll()) {
            allUsers.append(user.getUsername()).append("\n");
        }
        return allUsers.toString();
    }

    public UserDTO getMatchByTokenAndIndex(String token, int index) throws IOException {
        String username = convertTokenToUsername(token);

        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (user == null) {
            throw new RuntimeException("User not found with username: " + username);
        }
        System.out.println("Matches size: " + user.getMatches().size());
        System.out.println("index: " + index);

        if (index < 0 || index >= user.getMatches().size()) {
            throw new RuntimeException("Index out of bounds");
        }

        User theMatch = user.getMatches().get(index);

        return getProfile(theMatch.getUsername());

    }

    public int findAmtMatchesByToken(String token) {
        String username = convertTokenToUsername(token);

        User user = userRepository.findByUsername(username).isEmpty() ? null : userRepository.findByUsername(username).get();

        if (user == null) {
            throw new RuntimeException("User not found with username: " + username);
        }

        List<User> matchesByPreferences = userRepository.findMatchesByPreferences(user.getId(), user.getPreferences());
        List<User> matchesByDislikes = userRepository.findMatchesByDislikes(user.getId(), user.getDislikes());

        ArrayList<User> allMatches = new ArrayList<>();
        for (User match : matchesByDislikes) {
            if (matchesByPreferences.contains(match)) {
                allMatches.add(match);
            }
        }

        user.setMatches(allMatches);
        userRepository.save(user);

        return allMatches.size();
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

    public boolean deleteUser(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user != null) {
            userRepository.delete(user);
            return true;
        }
        return false;
    }

}
