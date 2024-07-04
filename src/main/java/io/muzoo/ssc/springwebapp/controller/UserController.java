package io.muzoo.ssc.springwebapp.controller;

import io.muzoo.ssc.springwebapp.models.User;
import io.muzoo.ssc.springwebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/api/user/{id}")
    @PreAuthorize("hasRole('USER')")
    public User getName(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/api/search")
    @PreAuthorize("hasRole('USER')")
    public User search(@RequestParam(required = false) String q) {
        return userService.search(q);
    }

    @GetMapping("/api/profile/{username}")
    @PreAuthorize("hasRole('USER')")
    public String getProfile(@PathVariable String username) {
        return userService.getProfile(username);
    }

}
