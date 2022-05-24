package com.example.cryptoWebsite.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping()
public class RegistrationController {

    private RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("register")
    public String register(RedirectAttributes redirectAttributes, @ModelAttribute ("registrationRequest") RegistrationRequest request){
        try{
             registrationService.register(request);
             return "redirect:/login";
        }
        catch (Exception e)
        {
           redirectAttributes.addFlashAttribute("error", e.getMessage());
           return "redirect:/error/info";
        }

    }

    @GetMapping("error/info")
    public String getError()
    {
        return "error_page";
    }

    @GetMapping
    public String getRegisterPage(Model model){
        model.addAttribute("registrationRequest", new RegistrationRequest());
        return "register_page";
    }

}
