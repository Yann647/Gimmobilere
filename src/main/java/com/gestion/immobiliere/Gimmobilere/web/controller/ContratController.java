package com.gestion.immobiliere.Gimmobilere.web.controller;

import com.gestion.immobiliere.Gimmobilere.model.Contrat;
import com.gestion.immobiliere.Gimmobilere.repository.ContratRepository;
import com.gestion.immobiliere.Gimmobilere.repository.ProprieteRepository;
import com.gestion.immobiliere.Gimmobilere.repository.ReservationRepository;
import com.gestion.immobiliere.Gimmobilere.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;

@Controller
public class ContratController {

    private final ContratRepository contratRepository;

    @Autowired
    private ProprieteRepository proprieteRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    public ContratController(ContratRepository contratRepository) {
        this.contratRepository = contratRepository;
    }

    @RequestMapping(value = "contrat/liste")
    public String listContrats(Model model) {
        model.addAttribute("contrats", contratRepository.findAll());
        return "contrat/liste";
    }

    @RequestMapping(value = "contrat/ajout")
    public String addContrat(Model model) {
        model.addAttribute("contrat", new Contrat());
        model.addAttribute("propriete", proprieteRepository.findAll());
        model.addAttribute("reservation", reservationRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        return "contrat/add";
    }

    @RequestMapping(value = "contrat/edit/{id}")
    public String editContrat(@PathVariable long id, Model model) {
        model.addAttribute("contrat", contratRepository.findById(id).orElse(null));
        model.addAttribute("propriete", proprieteRepository.findAll());
        model.addAttribute("reservation", reservationRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        return "contrat/edite";
    }

    @RequestMapping(value = "contrat/save", method = RequestMethod.POST)
    public String saveContrat(@ModelAttribute Contrat contrat) {
        contratRepository.save(contrat);
        return "redirect:/contrat/liste";
    }

    @RequestMapping(value = "contrat/update", method = RequestMethod.POST)
    public String updateContrat(@ModelAttribute Contrat contrat) {
        contratRepository.save(contrat);
        return "redirect:/contrat/liste";
    }

    @RequestMapping(value = "contrat/supprimer/{id}")
    public String deleteContrat(@PathVariable long id) {
        contratRepository.deleteById(id);
        return "redirect:/contrat/liste";
    }

}
