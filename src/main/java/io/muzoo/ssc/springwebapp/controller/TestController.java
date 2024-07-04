package io.muzoo.ssc.springwebapp.controller;

import io.muzoo.ssc.springwebapp.dto.UpdateUserRequest;
import io.muzoo.ssc.springwebapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;//package io.muzoo.ssc.springwebapp.controller;
import org.springframework.web.bind.annotation.RequestParam;

import io.muzoo.ssc.springwebapp.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @Autowired
    private UserService userService;

    @GetMapping("/anon")
    public String anonEndPoint() {
        return "everyone can see this";
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('USER')")
    public String usersEndPoint() {
        return "ONLY users can see this";
    }

    @GetMapping("/admins")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminsEndPoint() {
        return "ONLY admins can see this";
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('USER')")
    public User search(@RequestParam(required = false) String q) {
        return userService.search(q);
    }

    @GetMapping("/profile/{username}")
    @PreAuthorize("hasRole('USER')")
    public String getProfile(@PathVariable String username) {
        return userService.getProfile(username);
    }


}