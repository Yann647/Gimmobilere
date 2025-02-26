package com.gestion.immobiliere.Gimmobilere.web.controller;

import com.gestion.immobiliere.Gimmobilere.constant.Role;
import com.gestion.immobiliere.Gimmobilere.model.User;
import com.gestion.immobiliere.Gimmobilere.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    public RegisterController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "inscription")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "inscription";
    }

    @PostMapping("inscription")
    public String addUser(@ModelAttribute("user") User user) {
        user.setRole(Role.CLIENT);
        User savedUer = userRepository.save(user);
        return "redirect:/";
    }



}
