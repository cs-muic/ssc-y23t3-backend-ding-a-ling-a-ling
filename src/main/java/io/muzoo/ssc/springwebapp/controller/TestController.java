package io.muzoo.ssc.springwebapp.controller;

import io.muzoo.ssc.springwebapp.models.User;
import io.muzoo.ssc.springwebapp.repositories.UserRepository;
import io.muzoo.ssc.springwebapp.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.muzoo.ssc.springwebapp.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserController userController;

    @GetMapping("/anon")
    public String anonEndPoint() {
        return "everyone can see this";
    }

    @GetMapping("/users")
    public String usersEndPoint() {
        return userService.getAllUsers();
    }

    @GetMapping("/admins")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminsEndPoint() {
        return "ONLY admins can see this";
    }

    @GetMapping("/profile/{username}")
    @PreAuthorize("hasRole('USER')")
    public String getProfile(@PathVariable String username) {
        return userService.getProfile(username);
    }

    @PostMapping("/imgTest")
    public String uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("username") String username) throws IOException {
        if (imageService.saveImageToStorage(username, file)){
            return "Image uploaded successfully";
        } else {
            return "Failed to upload image";
        }
    }

}