package com.gestion.immobiliere.Gimmobilere.web.controller;

import com.gestion.immobiliere.Gimmobilere.model.Propriete;
import com.gestion.immobiliere.Gimmobilere.repository.ProprieteRepository;
import com.gestion.immobiliere.Gimmobilere.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProprieteController {

    private final ProprieteRepository proprieteRepository;

    @Autowired
    private UserRepository userRepository;

    public ProprieteController(ProprieteRepository proprieteRepository) {
        this.proprieteRepository = proprieteRepository;
    }

    @RequestMapping(value = "propriete/liste")
    public String listProprietes(Model model) {
        model.addAttribute("proprietes", proprieteRepository.findAll());
        return "propriete/liste";
    }

    @RequestMapping(value = "propriete/ajout")
    public String addPropriete(Model model) {
        model.addAttribute("propriete", new Propriete());
        model.addAttribute("users", userRepository.findAll());
        return "propriete/add";
    }

    @RequestMapping(value = "propriete/edit/{id}")
    public String editPropriete(@PathVariable long id, Model model) {
        model.addAttribute("propriete", proprieteRepository.findById(id).orElse(null));
        model.addAttribute("users", userRepository.findAll());
        return "propriete/edite";
    }

    @RequestMapping(value = "propriete/save", method = RequestMethod.POST)
    public String savePropriete(@ModelAttribute Propriete propriete) {
        proprieteRepository.save(propriete);
        return "redirect:/propriete/liste";
    }

    @RequestMapping(value = "propriete/update", method = RequestMethod.POST)
    public String updatePropriete(@ModelAttribute Propriete propriete) {
        proprieteRepository.save(propriete);
        return "redirect:/propriete/liste";
    }

    @RequestMapping(value = "propriete/supprimer/{id}")
    public String deletePropriete(@PathVariable long id) {
        proprieteRepository.deleteById(id);
        return "redirect:/propriete/liste";
    }

    @RequestMapping(value = "propriete/visionner/{id}")
    public String visionnerPropriete(@PathVariable long id, Model model) {
        Propriete propriete = proprieteRepository.findById(id).orElse(null);
        if (propriete == null) {
            return "redirect:/propriete/liste";
        }
        model.addAttribute("propriete", propriete);
        return "propriete/visionner";
    }

}
