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
        return new UserDTO(username, username);
    }

    public String createUser(String username, String name, String address, String group) {
        User user = new User();
        user.setUsername(username);
        user.setAddress(address);
        // make this setting the set in this user by gathered the list of dislikes they've chosen or smth
//        user.setDislikes();

        userRepository.save(user);
        return "OK";
    }

    public Set<User> findUsersWithSimilarDislikes(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return userRepository.findUsersWithCommonDislikes(user.getDislikes()); // Assuming this method is implemented in UserRepository
    }
}
