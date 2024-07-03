package io.muzoo.ssc.springwebapp.controller;


import io.muzoo.ssc.springwebapp.dto.AuthenticationResponse;
import io.muzoo.ssc.springwebapp.dto.SignInRequest;
import io.muzoo.ssc.springwebapp.dto.SignUpRequest;
import io.muzoo.ssc.springwebapp.service.AuthenticationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public AuthenticationResponse signup(@RequestBody SignUpRequest request) {
        return authenticationService.signup(request);
    }

    @PostMapping("/signin")
    public AuthenticationResponse signin(@RequestBody SignInRequest request) {
        return authenticationService.signin(request);
    }

    @GetMapping("/allusers")
    public String allusers() {
        return authenticationService.getAllUsers();
    }


}