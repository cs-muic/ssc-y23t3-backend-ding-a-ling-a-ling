package io.muzoo.ssc.springwebapp.controller;

import io.muzoo.ssc.springwebapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;//package io.muzoo.ssc.springwebapp.controller;
import org.springframework.web.bind.annotation.RequestParam;

import io.muzoo.ssc.springwebapp.SimpleService;
import io.muzoo.ssc.springwebapp.dto.UserDTO;
import io.muzoo.ssc.springwebapp.models.User;
import io.muzoo.ssc.springwebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/update") //TODO: can change the para, to restrict user to change stuff
    @PreAuthorize("hasRole('USER')")
    public String updateUser(
            @RequestParam String username,
            @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam String address,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String phoneNumber,
            @RequestParam double height,
            @RequestParam String displayName,
            @RequestParam String profilePicture,
            @RequestParam String contact,
            @RequestParam String biography,
            @RequestParam Set<String> preferences,
            @RequestParam Set<String> dislikes
    ) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setEmail(email);
        userDTO.setPassword(password);
        userDTO.setFirstName(firstname);
        userDTO.setLastName(lastname);
        userDTO.setAddress(address);
        userDTO.setHeight(height);
        userDTO.setDisplayName(displayName);
        userDTO.setProfilePicture(profilePicture); // should be the bucket url
        userDTO.setContact(contact);
        userDTO.setPhoneNumber(phoneNumber);
        userDTO.setBiography(biography);
        userDTO.setPreferences(preferences);
        userDTO.setDislikes(dislikes);
        return userService.updateUser(userDTO);
    }




}

//@RestController
//@RequestMapping("/api")
//public class TestController {
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private SimpleService simpleService;
//
//    @GetMapping("/user/{id}")
//    public User getName(@PathVariable Long id) {
//        return userService.getUser(id);
//    }
//
//    @PostMapping("/user/create")
//    @PreAuthorize("hasRole('USER')")
//    public String createUser(
//            @RequestParam String username,
//            @RequestParam String firstname,
//            @RequestParam String lastname,
//            @RequestParam String address,
//            @RequestParam String email,
//            @RequestParam String password,
//            @RequestParam String phone,
//            @RequestParam int age,
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
//        userDTO.setAge(age);
//        userDTO.setHeight(height);
//        userDTO.setDisplayName(displayName);
//        userDTO.setProfilePicture(profilePicture); // should be the bucket url
//        userDTO.setContact(contact);
//        userDTO.setPhoneNumber(phone);
//        userDTO.setBiography(biography);
//        userDTO.setPreferences(preferences);
//        userDTO.setDislikes(dislikes);
//        return userService.createUser(userDTO);
//    }
//
//    @PostMapping("/api/user/update") //TODO: can change the para, to restrict user to change stuff
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
//////
//    @GetMapping("/search")
//    public User search(@RequestParam(required = false) String q) {
//        return userService.search(q);
//    }
//
//    @GetMapping("/profile/{username}")
//    public String getProfile(@PathVariable String username) {
//        return userService.getProfile(username);
//    }
//}