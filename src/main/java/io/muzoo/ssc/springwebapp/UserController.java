package io.muzoo.ssc.springwebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SimpleService simpleService;

    @GetMapping("/api/user/{id}")
    public User getName(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping("/api/user/create")
    public String createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @PostMapping("/api/user/update")
    public String updateUser(@RequestBody UserDTO userDTO) {
        return userService.updateUser(userDTO);
    }

    @GetMapping("/api/search")
    public User search(@RequestParam(required = false) String q) {
        return userService.search(q);
    }

    @GetMapping("/api/profile/{username}")
    public UserDTO getProfile(@PathVariable String username) {
        return userService.getProfile(username);
    }
}
