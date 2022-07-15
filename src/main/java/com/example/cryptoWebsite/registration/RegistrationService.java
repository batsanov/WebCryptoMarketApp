package com.example.cryptoWebsite.registration;

import com.example.cryptoWebsite.user.User;
import com.example.cryptoWebsite.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private final UserService userService;

    public RegistrationService(UserService userService) {
        this.userService = userService;
    }

    public boolean register(RegistrationRequest request) {
        boolean isValidEmail = EmailValidator.validate(request.getEmail());
        if(!isValidEmail){
            throw new IllegalStateException("Email not valid!");
        }
        if (!request.getPassword().equals(request.getConfirmPassword())){
            throw new IllegalStateException("Passwords doesn't match!");
        }
        if (request.getPassword().length() == 0){
            throw new IllegalStateException("Please enter password!");
        }


        return userService.registerUser(
                new User(request.getUsername(), request.getPassword(), request.getEmail()));
    }
}
