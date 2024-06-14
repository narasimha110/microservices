package com.demo.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SendOtpEmailApplication {

	public static void main(String[] args) {
		SpringApplication.run(SendOtpEmailApplication.class, args);
		
		System.out.println("update");
		System.out.println("newupdate");
	}

}
