package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

import com.example.demo.models.UserInformationEntity;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.demo")
public class WebSocketServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebSocketServerApplication.class, args);
	}

}
