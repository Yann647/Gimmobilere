package com.gestion.immobiliere.Gimmobilere.web.controller;


import com.gestion.immobiliere.Gimmobilere.repository.ContratRepository;
import com.gestion.immobiliere.Gimmobilere.repository.ProprieteRepository;
import com.gestion.immobiliere.Gimmobilere.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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



    @RequestMapping("/dashboard")
    public String getDashboard(Model model){
        // Statistiques de base
        model.addAttribute("totalProperties", proprieteRepository.count());
        model.addAttribute("totalUsers", userRepository.count());
        model.addAttribute("totalContrats", contratRepository.count());

        // Pour les ventes et locations, vous pouvez adapter selon votre logique métier
        // Pour l'instant, on utilise le même count que les contrats
        model.addAttribute("totalRentals", contratRepository.count());
        model.addAttribute("totalSales", 0); // À adapter selon votre logique

        // Liste des utilisateurs pour le tableau
        model.addAttribute("users", userRepository.findAll());

        return "dashboard";
    }
}
