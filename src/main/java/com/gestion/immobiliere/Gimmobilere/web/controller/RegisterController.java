package com.gestion.immobiliere.Gimmobilere.web.controller;

import com.gestion.immobiliere.Gimmobilere.model.User;
import com.gestion.immobiliere.Gimmobilere.service.UserAccountService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class RegisterController {

    private final UserAccountService userAccountService;

    public RegisterController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping("/inscription")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "inscription";
    }

    @PostMapping("/inscription")
    public String register(
            @ModelAttribute("user") User user,
            Model model) {

        try {
            userAccountService.registerClient(user);
            return "redirect:/login?registered";

        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("user", user);
            user.setPassword("");
            return "inscription";
        }
    }



}
