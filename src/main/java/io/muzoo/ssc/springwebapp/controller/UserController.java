package io.muzoo.ssc.springwebapp.controller;

import io.muzoo.ssc.springwebapp.dto.UpdateUserRequest;
import io.muzoo.ssc.springwebapp.dto.UserDTO;
import io.muzoo.ssc.springwebapp.models.User;
import io.muzoo.ssc.springwebapp.service.ImageService;
import io.muzoo.ssc.springwebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;


    @GetMapping("/user/{id}")
    @PreAuthorize("hasRole('USER')")
    public User getName(@PathVariable Long id) {
        return userService.getUser(id);
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

    @PostMapping("/update") //TODO: can change the para, to restrict user to change stuff
    @PreAuthorize("hasRole('USER')")
    public String updateUser(@ModelAttribute UpdateUserRequest updateUserRequest) throws IOException {
        return userService.updateUser(updateUserRequest);
    }

    @GetMapping("/matches")
    public List<User> getMatchesByDislikes(@RequestParam("username") String username) {
        return userService.findMatchesByUserDislikes(username);
    }

    @GetMapping("/image/{username}")
    public String getImage(@PathVariable String username) throws IOException {
        return imageService.getImage(username);
    }

}

