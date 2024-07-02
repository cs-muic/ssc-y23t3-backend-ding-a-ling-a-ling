package io.muzoo.ssc.springwebapp.service;

import io.muzoo.ssc.springwebapp.dto.AuthenticationResponse;
import io.muzoo.ssc.springwebapp.dto.UserDTO;
import io.muzoo.ssc.springwebapp.models.User;
import io.muzoo.ssc.springwebapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {



    private final UserRepository userRepository;

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }

        };
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User search(String username) {
        return userRepository.findByUsername(username);
    }

    public String getProfile(String username) {
        User user = userRepository.findByUsername(username);
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
            profiles.append("Profile Picture: ").append(user.getProfilePicture()).append("\n");
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


    public String updateUser(UserDTO userDTO) {
        User user = userRepository.findByUsername(userDTO.getUsername());
        if (user != null) {
            setUserInfo(user, userDTO);
            userRepository.save(user);
            return String.format("Updated user %s successfully", userDTO.getUsername());
        }
        return "User not found";
    }

    public Set<User> match(String username) {
        return userRepository.findByDislikes(userRepository.findByUsername(username).getDislikes());
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
        user.setProfilePicture(userDTO.getProfilePicture());
        user.setContact(userDTO.getContact());
        user.setBiography(userDTO.getBiography());
        user.setPreferences(userDTO.getPreferences());
        user.setDislikes(userDTO.getDislikes());

    }

    public User save(User newUser) {
        if (newUser.getId() == null) {
            newUser.setCreatedAt(LocalDateTime.now());
        }

        newUser.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(newUser);
    }

}
