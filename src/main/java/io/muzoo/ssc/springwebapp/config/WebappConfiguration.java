package io.muzoo.ssc.springwebapp.config;


import io.muzoo.ssc.springwebapp.SimpleService;
import io.muzoo.ssc.springwebapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration

@CrossOrigin(origins = "http://172.17.0.1:3000")

public class WebappConfiguration {

    @Value("${ssc.simple.service.url:http://172.17.0.1:3000}")
    private String url;

    @Bean
    public SimpleService createSimpleService(UserRepository userRepository) {
        SimpleService simpleService = new SimpleService();
        simpleService.setUserRepository(userRepository);
        simpleService.setUrl(url);
        return simpleService;
    }
}
