package io.muzoo.ssc.springwebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
            @RequestParam String name,
            @RequestParam String address,
            @RequestParam String group
    ) {
        return userService.createUser(username, name, address, group);
    }

    @GetMapping("/api/search")
    public User search(@RequestParam(required = false) String q) {
        return userService.search(q);
    }


    @GetMapping("/api/profile/{username}")
    public UserDTO getProfile(@PathVariable String username) {
        return userService.getProfile(username);
    }

    @GetMapping("/api/profile/{username}/group/{groupId}")
    public UserDTO getProfile2(@PathVariable String username, @PathVariable int groupId) {
        return userService.getProfile(username);
    }

}
