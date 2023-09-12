package com.example.bloggingApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BloggingAppApplication {
private final Logger logger = LoggerFactory.getLogger(BloggingAppApplication.class);
	public static void main(String[] args) {

		SpringApplication.run(BloggingAppApplication.class, args);
	}

}
