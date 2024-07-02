package io.muzoo.ssc.springwebapp.config;


import io.muzoo.ssc.springwebapp.SimpleService;
import io.muzoo.ssc.springwebapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@CrossOrigin(origins = "http://localhost:3000") // Adjust the allowed origin accordingly

public class WebappConfiguration {

    @Value("${ssc.simple.service.url:http://localhost:3000}")
    private String url;

    @Autowired
    private UserRepository userRepository;

    @Bean
    public SimpleService createSimpleService(UserRepository userRepository) {
        SimpleService simpleService = new SimpleService();
        simpleService.setUserRepository(userRepository);
        simpleService.setUrl(url);
        System.out.println("SimpleService is created. " + url   );
        return simpleService;
    }
}