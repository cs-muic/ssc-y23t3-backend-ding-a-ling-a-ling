package io.muzoo.ssc.springwebapp.dto;

import io.muzoo.ssc.springwebapp.SimpleService;
import io.muzoo.ssc.springwebapp.models.User;
import io.muzoo.ssc.springwebapp.service.UserService;
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
    public String createUser(
            @RequestParam String username,
            @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam String address,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String phone,
            @RequestParam int age,
            @RequestParam double height,
            @RequestParam String displayName,
            @RequestParam String profilePicture,
            @RequestParam String contact,
            @RequestParam String biography,
            @RequestParam Set<String> preferences,
            @RequestParam Set<String> dislikes
    ){
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setEmail(email);
        userDTO.setPassword(password);
        userDTO.setFirstName(firstname);
        userDTO.setLastName(lastname);
        userDTO.setAddress(address);
        userDTO.setAge(age);
        userDTO.setHeight(height);
        userDTO.setDisplayName(displayName);
        userDTO.setProfilePicture(profilePicture); // should be the bucket url
        userDTO.setContact(contact);
        userDTO.setPhoneNumber(phone);
        userDTO.setBiography(biography);
        userDTO.setPreferences(preferences);
        userDTO.setDislikes(dislikes);
        return userService.createUser(userDTO);
    }

    @PostMapping("/api/user/update") //TODO: can change the para, to restrict user to change stuff
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
    ){
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

    @GetMapping("/api/search")
    public User search(@RequestParam(required = false) String q) {
        return userService.search(q);
    }

    @GetMapping("/api/profile/{username}")
    public String getProfile(@PathVariable String username) {
        return userService.getProfile(username);
    }

//    @GetMapping("/api/login")
//    public User login(
//            @RequestParam String username,
//            @RequestParam String password
//    ){
//        return userService.getLogin(username, password);
//    }


}
