package com.gestion.immobiliere.Gimmobilere.web.controller;

import com.gestion.immobiliere.Gimmobilere.model.User;
import com.gestion.immobiliere.Gimmobilere.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/profil")
public class ProfilController {

    private final UserRepository userRepository;

    public ProfilController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Afficher le profil de l'utilisateur connecté
    @GetMapping
    public String profil(HttpSession session, Model model) {
        // Récupérer l'utilisateur connecté depuis la session
        User connectedUser = (User) session.getAttribute("user");

        // Vérifier si l'utilisateur est connecté
        if (connectedUser == null) {
            return "redirect:/login";
        }

        // Charger les données à jour depuis la base de données
        User user = userRepository.findById(connectedUser.getId()).orElse(null);
        if (user == null) {
            return "redirect:/login";
        }

        // Ajouter l'utilisateur au modèle pour l'affichage
        model.addAttribute("user", user);
        return "profil";
    }
}
