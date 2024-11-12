package com.dr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DrApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrApplication.class, args);
	}

}
