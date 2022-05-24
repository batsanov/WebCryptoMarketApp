package com.example.cryptoWebsite.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
@Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository){
    return args -> {
        User tototest = new User("toto", "password", "totomail@gmail.com");
        userRepository.save(tototest);
    };
}
}

