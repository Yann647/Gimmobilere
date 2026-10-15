package com.gestion.immobiliere.Gimmobilere.web.controller;

import com.gestion.immobiliere.Gimmobilere.repository.ContratRepository;
import com.gestion.immobiliere.Gimmobilere.repository.ProprieteRepository;
import com.gestion.immobiliere.Gimmobilere.repository.UserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final UserRepository userRepository;
    private final ProprieteRepository proprieteRepository;
    private final ContratRepository contratRepository;

    public DashboardController(UserRepository userRepository,
                               ProprieteRepository proprieteRepository,
                               ContratRepository contratRepository) {
        this.userRepository = userRepository;
        this.proprieteRepository = proprieteRepository;
        this.contratRepository = contratRepository;
    }


    @GetMapping({"/dashboard"})
    public String getDashboard(Model model, Authentication authentication) {

        boolean connected = authentication != null
                && authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken);

        String role = "";

        if (connected) {
            role = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .findFirst()
                    .orElse("");
        }

        model.addAttribute("connected", connected);
        model.addAttribute("role", role);

        // Statistiques
        model.addAttribute("totalProperties", proprieteRepository.count());
        model.addAttribute("totalUsers", userRepository.count());
        model.addAttribute("totalContrats", contratRepository.count());
        model.addAttribute("totalRentals", contratRepository.count());
        model.addAttribute("totalSales", 0);

        // Liste des utilisateurs
        model.addAttribute("users", userRepository.findAll());

        return "dashboard";
    }
}
