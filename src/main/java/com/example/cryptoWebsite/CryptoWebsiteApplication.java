package com.example.cryptoWebsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.File;

@SpringBootApplication
@EnableScheduling
public class CryptoWebsiteApplication {
	public static Long loggedUserId = 1l;
	public static void main(String[] args) {
		SpringApplication.run(CryptoWebsiteApplication.class, args);
	}

}
