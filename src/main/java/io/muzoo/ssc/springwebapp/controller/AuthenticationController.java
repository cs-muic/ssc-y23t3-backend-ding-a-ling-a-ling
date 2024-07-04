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


}