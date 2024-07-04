package io.muzoo.ssc.springwebapp.controller;


import io.muzoo.ssc.springwebapp.dto.AuthenticationResponse;
import io.muzoo.ssc.springwebapp.dto.SignInRequest;
import io.muzoo.ssc.springwebapp.dto.SignUpRequest;
import io.muzoo.ssc.springwebapp.dto.UserDTO;
import io.muzoo.ssc.springwebapp.models.User;
import io.muzoo.ssc.springwebapp.service.AuthenticationService;
import io.muzoo.ssc.springwebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import lombok.RequiredArgsConstructor;

import java.util.Set;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    private final UserService userService;

    @PostMapping("/signup")
    public AuthenticationResponse signup(@RequestBody SignUpRequest request) {
        return authenticationService.signup(request);
    }

    @PostMapping("/signin")
    public AuthenticationResponse signin(@RequestBody SignInRequest request) {
        return authenticationService.signin(request);
    }

//    @GetMapping("/search")
//    @PreAuthorize("hasRole('USER')")
//    public User search(@RequestParam(required = false) String q) {
//        return userService.search(q);
//    }

//    @GetMapping("/profile/{username}")
//    @PreAuthorize("hasRole('USER')")
//    public String getProfile(@PathVariable String username) {
//        return userService.getProfile(username);
//    }

//    @PostMapping("/update") //TODO: can change the para, to restrict user to change stuff
//    @PreAuthorize("hasRole('USER')")
//    public String updateUser(
//            @RequestParam String username,
//            @RequestParam String firstname,
//            @RequestParam String lastname,
//            @RequestParam String address,
//            @RequestParam String email,
//            @RequestParam String password,
//            @RequestParam String phoneNumber,
//            @RequestParam double height,
//            @RequestParam String displayName,
//            @RequestParam String profilePicture,
//            @RequestParam String contact,
//            @RequestParam String biography,
//            @RequestParam Set<String> preferences,
//            @RequestParam Set<String> dislikes
//    ) {
//        UserDTO userDTO = new UserDTO();
//        userDTO.setUsername(username);
//        userDTO.setEmail(email);
//        userDTO.setPassword(password);
//        userDTO.setFirstName(firstname);
//        userDTO.setLastName(lastname);
//        userDTO.setAddress(address);
//        userDTO.setHeight(height);
//        userDTO.setDisplayName(displayName);
//        userDTO.setProfilePicture(profilePicture); // should be the bucket url
//        userDTO.setContact(contact);
//        userDTO.setPhoneNumber(phoneNumber);
//        userDTO.setBiography(biography);
//        userDTO.setPreferences(preferences);
//        userDTO.setDislikes(dislikes);
//        return userService.updateUser(userDTO);
//    }

}