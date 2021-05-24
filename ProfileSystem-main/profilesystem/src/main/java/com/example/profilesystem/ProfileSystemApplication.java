package com.example.profilesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan("com.example.profilesystem")
@EntityScan("com.example.profilesystem")
@EnableAutoConfiguration
@EnableScheduling
public class ProfileSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfileSystemApplication.class, args);
		System.out.println("succesfully started application");
	}

}
