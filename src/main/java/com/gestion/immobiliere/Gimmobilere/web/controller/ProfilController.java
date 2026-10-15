package com.gestion.immobiliere.Gimmobilere.web.controller;

import com.gestion.immobiliere.Gimmobilere.model.User;
import com.gestion.immobiliere.Gimmobilere.repository.UserRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.Authentication;

@Controller
@RequestMapping("/profil")
public class ProfilController {

    private final UserRepository userRepository;

    public ProfilController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Afficher le profil de l'utilisateur connecté
    @GetMapping
    public String profil(Authentication authentication, Model model) {

        // Vérifier que l'utilisateur est connecté
        if (authentication == null
                || !authentication.isAuthenticated()
                || authentication.getName() == null) {
            return "redirect:/login";
        }

        // Rechercher l'utilisateur connecté grâce à son login
        User user = userRepository.findByLogin(authentication.getName())
                .orElse(null);

        if (user == null) {
            return "redirect:/login";
        }

        // Envoyer l'utilisateur à la page Thymeleaf
        model.addAttribute("user", user);
        return "profil";
    }
}
