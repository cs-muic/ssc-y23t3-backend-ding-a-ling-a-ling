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

    @PostMapping("/update") //TODO: can change the para, to restrict user to change stuff
    public String updateUser(@ModelAttribute UpdateUserRequest updateUserRequest) throws IOException {
        return userService.updateUser(updateUserRequest);
    }

    @GetMapping("/matches")
    public List<User> getMatchesByDislikes(@RequestParam("token") String token) {
        return userService.findMatchesByToken(token);
    }

    @GetMapping("/image")
    public String getImage(@RequestParam String username) throws IOException {
        return imageService.getImage(username);
    }

    @GetMapping("/preferences")
    public List<String> userPref(@RequestParam("token") String token) {
        return userService.getUserPreferences(token);
    }

    @GetMapping("/dislikes")
    public List<String> userDislikes(@RequestParam("token") String token) {
        return userService.getUserDislikes(token);
    }

}

