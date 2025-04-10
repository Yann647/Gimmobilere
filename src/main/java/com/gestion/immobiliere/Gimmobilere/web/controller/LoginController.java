package com.gestion.immobiliere.Gimmobilere.web.controller;

import com.gestion.immobiliere.Gimmobilere.model.User;
import com.gestion.immobiliere.Gimmobilere.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(User user, Model model, HttpSession session) {
        User loggedInUser = userRepository.findByLoginAndPassword(user.getLogin(),user.getPassword());
        System.out.println("use" + loggedInUser.toString());
        if (loggedInUser != null) {
            session.setAttribute("user",loggedInUser);
            return "redirect:/";
        } else {
            model.addAttribute("error", "Identifiants invalides");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Clear the session
        session.invalidate();
        return "redirect:/login";
    }

}
