package com.timelec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TimelecApplication {
		
	public static void main(String[] args) {
		SpringApplication.run(TimelecApplication.class, args); 
	}
}