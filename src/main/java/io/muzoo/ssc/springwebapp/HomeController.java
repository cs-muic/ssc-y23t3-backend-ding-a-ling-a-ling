package io.muzoo.ssc.springwebapp;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "I'm home.";
    }

    @PostMapping("/")
    public String homePost() {
        return "I'm home. (POST)";
    }

}
