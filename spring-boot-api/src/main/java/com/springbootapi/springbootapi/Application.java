package com.springbootapi.springbootapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.springbootapi.springbootapi")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
