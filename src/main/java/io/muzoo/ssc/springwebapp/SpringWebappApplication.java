package io.muzoo.ssc.springwebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
@SpringBootApplication(scanBasePackages = "io.muzoo.ssc.springwebapp")
public class SpringWebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebappApplication.class, args);
	}

}
