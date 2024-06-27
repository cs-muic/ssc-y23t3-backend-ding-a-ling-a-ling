package io.muzoo.ssc.springwebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User search(String username) {
        return userRepository.findByUsername(username);
    }

    public UserDTO getProfile(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return new UserDTO(user);
        }
        return null;
    }

    public String createUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setAge(userDTO.getAge());
        user.setHeight(userDTO.getHeight());
        user.setDisplayName(userDTO.getDisplayName());
        user.setProfilePicture(userDTO.getProfilePicture());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setBiography(userDTO.getBiography());
        user.setPreferences(userDTO.getPreferences());
        user.setDislikes(userDTO.getDislikes());

        userRepository.save(user);
        return String.format("Created user %s successfully", userDTO.getUsername());
    }

    public String updateUser(UserDTO userDTO) {
        User user = userRepository.findByUsername(userDTO.getUsername());
        if (user != null) {
            user.setUsername(userDTO.getUsername());
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setAge(userDTO.getAge());
            user.setHeight(userDTO.getHeight());
            user.setDisplayName(userDTO.getDisplayName());
            user.setProfilePicture(userDTO.getProfilePicture());
            user.setPhoneNumber(userDTO.getPhoneNumber());
            user.setBiography(userDTO.getBiography());
            user.setPreferences(userDTO.getPreferences());
            user.setDislikes(userDTO.getDislikes());

            userRepository.save(user);
            return String.format("Updated user %s successfully", userDTO.getUsername());
        }
        return "User not found";
    }

    public Set<User> findUsersWithSimilarDislikes(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return userRepository.findUsersWithCommonDislikes(user.getDislikes()); // Assuming this method is implemented in UserRepository
    }

    public Set<User> findUsersWithSimilarDislikes(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return userRepository.findUsersWithCommonDislikes(user.getDislikes()); // Assuming this method is implemented in UserRepository
    }
}
