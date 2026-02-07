package com.gestion.immobiliere.Gimmobilere.web.controller;

import com.gestion.immobiliere.Gimmobilere.model.Propriete;
import com.gestion.immobiliere.Gimmobilere.repository.ProprieteRepository;
import com.gestion.immobiliere.Gimmobilere.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ProprieteController {

    private final ProprieteRepository proprieteRepository;
    private final String UPLOAD_DIR = "uploads/";

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
    public String savePropriete(@ModelAttribute Propriete propriete,
                                @RequestParam(value = "image", required = false) MultipartFile image)
            throws IOException {
        // Gestion de l'upload d'image
        if (image != null && !image.isEmpty()) {
            String imagePath = uploadImage(image);
            propriete.setImagePath(imagePath);
        }
        proprieteRepository.save(propriete);
        return "redirect:/propriete/liste";
    }

    @RequestMapping(value = "propriete/update", method = RequestMethod.POST)
    public String updatePropriete(@ModelAttribute Propriete propriete,
                                  @RequestParam(value = "image", required = false) MultipartFile image,
                                  @RequestParam(value = "supprimerImage", defaultValue = "false") boolean supprimerImage)
            throws IOException {
        // Si une nouvelle image est fournie, l'uploader
        if (image != null && !image.isEmpty()) {
            String imagePath = uploadImage(image);
            propriete.setImagePath(imagePath);
        } else {
            if (supprimerImage) {
                propriete.setImagePath(null);
            } else {
                // Si pas de nouvelle image, conserver l'ancienne imagePath
                // Récupérer la propriété existante pour garder son image
                Propriete existingPropriete = proprieteRepository.findById(propriete.getId()).orElse(null);
                if (existingPropriete != null && existingPropriete.getImagePath() != null) {
                    propriete.setImagePath(existingPropriete.getImagePath());
                }
            }
        }

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

    private String uploadImage(MultipartFile image) throws IOException {
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR + fileName);
        Files.write(filePath, image.getBytes());

        return fileName;
    }

}
