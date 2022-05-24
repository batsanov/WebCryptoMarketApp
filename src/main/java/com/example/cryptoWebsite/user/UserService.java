package com.example.cryptoWebsite.user;

import com.example.cryptoWebsite.CryptoWebsiteApplication;
import com.example.cryptoWebsite.registration.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserByUsername(String username){return userRepository.findUserByUsername(username);}

    public Optional<User> getUserById(Long id){return userRepository.findById(id);}

    public User getCurrentUser(){return userRepository.findById(CryptoWebsiteApplication.loggedUserId).get();}

    public boolean registerUser(User user){
        Optional<User> testUser;

    testUser = userRepository.findUserByEmail(user.getEmail());
    if (testUser.isPresent()){
        throw new IllegalStateException("Email already in use!");
    }

    testUser = userRepository.findUserByUsername(user.getUsername());
    if(testUser.isPresent()){
        throw new IllegalStateException("Username already in use!");
    }

    userRepository.save(user);
    return true;
    }

    @Transactional
    public boolean updateUser(Long userId, String username, String email, String password) {
       User user = userRepository.findById(userId).orElseThrow( () ->
       new IllegalStateException("User with " + userId + " doesn't exist!" ));

       Optional<User> userTest;

       if(username != null && !username.equals(user.getUsername())  && username.length() > 0){
           userTest = userRepository.findUserByUsername(username);

           if (userTest.isPresent())
               throw new IllegalStateException("Username already in use!");

           user.setUsername(username);
       }



       if (email != null && !email.equals(user.getEmail()) && email.length() > 0 ) {
           userTest = userRepository.findUserByEmail(email);


           if (userTest.isPresent())
               throw new IllegalStateException("Email already in use!");

           EmailValidator validator = new EmailValidator();
           boolean isValid = validator.validate(email);

           if (!isValid)
           throw new IllegalStateException("Email not valid!");

           user.setEmail(email);
       }

        if(password != null && !password.equals(user.getPassword()) && password.length() > 0 )
        user.setPassword(password);

        return true;
    }

    public Possessions getPossessions() {
        Optional<User> user = userRepository.findById(CryptoWebsiteApplication.loggedUserId);
        if(!user.isPresent())
            throw new IllegalStateException("No logged user!");

      return  user.get().getPossessions();
    }

    @Transactional
    public void updateImage(Long userId, MultipartFile file) {
        String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/images";
        String toLoad = "/images/" + file.getOriginalFilename();;
        Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
        try {
            Files.write(fileNameAndPath, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        User user = userRepository.findById(userId).get();
        user.setProfilePicture(toLoad);
    }
}
