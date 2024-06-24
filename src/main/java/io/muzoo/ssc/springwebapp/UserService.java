package io.muzoo.ssc.springwebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        user.setName(name);
        user.setUsername(username);
        user.setAddress(address);
        user.setGroup(group);

        userRepository.save(user);
        return "OK";
    }
}
