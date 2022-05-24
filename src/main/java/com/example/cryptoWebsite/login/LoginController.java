package com.example.cryptoWebsite.login;

import com.example.cryptoWebsite.CryptoWebsiteApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(path ="login")
public class LoginController {
    LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping()
    String login(RedirectAttributes redirectAttributes, @ModelAttribute("loginRequest") LoginRequest request){
        try {
            loginService.login(request);
            return "redirect:trading/page";
        }
        catch (Exception e){
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:error/info";
        }

    }

    @GetMapping(path = "currentSession")
    String currentSessionId(){
        return CryptoWebsiteApplication.loggedUserId.toString();
    }

    @GetMapping()
    public String getLoginPage(Model model){
        model.addAttribute("loginRequest", new LoginRequest());
        return "login_page";
    }
}
