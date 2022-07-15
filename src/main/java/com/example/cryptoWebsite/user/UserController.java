package com.example.cryptoWebsite.user;


import com.example.cryptoWebsite.CryptoWebsiteApplication;
import com.example.cryptoWebsite.prices.PricesController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;



@Controller
@RequestMapping(path= "user")
public class UserController {
    private final UserService userService;
    private final PricesController pricesController;

    @Autowired
    public UserController(UserService userService, PricesController pricesController){
        this.userService = userService;
        this.pricesController = pricesController;
    }

    @GetMapping
    List<User> getAllUsers(){
        return userService.getAllUsers();
    }


    @GetMapping(path = "profile")
    public String getPossessions(RedirectAttributes redirectAttributes, Model model){
        try{
            Possessions possessions = userService.getPossessions();
            User user = userService.getCurrentUser();

            model.addAttribute("possessionsMap", possessions.toMap());
            model.addAttribute("user", user);
            model.addAttribute("possessions", possessions);
            model.addAttribute("prices", pricesController.getCurrentPrices());

            return "profile_info_page";
        }
        catch (Exception e){
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/error/info";
        }


    }

    @PostMapping(path = "edit/{userId}")
    public String updateUser(@PathVariable("userId") Long userId,
                             @RequestParam(required = false) String username,
                             @RequestParam(required = false) String email,
                             @RequestParam(required = false) String password,
                             RedirectAttributes redirectAttributes)

    {
        try{
            userService.updateUser(userId, username, email, password);
            return "redirect:/user/profile";
        } catch (Exception e){
                redirectAttributes.addFlashAttribute("error", e.getMessage());
                return "redirect:/user/error/update";
        }

    }

    @GetMapping("error/update")
    public String getError()
    {
        return "error_update";
    }

    @PostMapping(path = "profile/edit/image/{userId}")
    public String updateImage(@PathVariable("userId") Long userId,
                              @RequestParam("fileImage")MultipartFile file) {

       userService.updateImage(userId,file);

        return "redirect:/user/profile";
    }

    @GetMapping(path = "profile/edit/image")
    public String getUpdateImage(Model model){
        User user = userService.getCurrentUser();
        model.addAttribute("user", user);
        return "edit_image";
    }

    @GetMapping(path="logout")
    public String logout(){
        CryptoWebsiteApplication.loggedUserId = 0L;
        return "redirect:/login";
    }



    @GetMapping(path="profile/edit/{toEdit}")
    public String getEdit(Model model, @PathVariable("toEdit") String toEdit){

        User user = userService.getCurrentUser();
        String currentValue = "";
        switch (toEdit){
            case "username" : currentValue =  user.getUsername();
            break;
            case "password" : currentValue = user.getPassword();
            break;
            case "email" : currentValue = user.getEmail();
        }

        model.addAttribute("toEdit", toEdit);
        model.addAttribute("input",new UserEditRequest(toEdit));
        model.addAttribute("currentValue",currentValue);
        model.addAttribute("user", user);

        return "edit_page";
    }

}
