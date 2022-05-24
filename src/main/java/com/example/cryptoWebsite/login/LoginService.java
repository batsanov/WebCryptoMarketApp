package com.example.cryptoWebsite.login;

import com.example.cryptoWebsite.CryptoWebsiteApplication;
import com.example.cryptoWebsite.user.User;
import com.example.cryptoWebsite.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    private final UserService userService;

    @Autowired
    public LoginService(UserService userService) {
        this.userService = userService;
    }

    public boolean login(LoginRequest request) {
        Optional<User> user = userService.getUserByUsername(request.getUsername());

        if(!user.isPresent())
            throw new IllegalStateException("Invalid username!");
        else if(!user.get().getPassword().equals(request.getPassword())) {
            throw new IllegalStateException("Invalid password!");
        }
        else
        {
            CryptoWebsiteApplication.loggedUserId = user.get().getId();
            return true;
        }
    }
}
