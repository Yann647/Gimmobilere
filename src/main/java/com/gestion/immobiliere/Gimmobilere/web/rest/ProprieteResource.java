package com.gestion.immobiliere.Gimmobilere.web.rest;

import com.gestion.immobiliere.Gimmobilere.model.Propriete;
import com.gestion.immobiliere.Gimmobilere.repository.ProprieteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/proprietes")
public class ProprieteResource {

    @Autowired
    private ProprieteRepository proprieteRepository;

    private final String UPLOAD_DIR = "uploads/";

    @GetMapping("/liste")
    public List<Propriete> listProprietes() {
        return proprieteRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Propriete> getProprieteById(@PathVariable Long id) {
        return proprieteRepository.findById(id);
    }

    @PostMapping("/ajout")
    public Optional<Propriete> addPropriete(@RequestBody Propriete propriete) {
        return Optional.of(proprieteRepository.save(propriete));
    }

    // Nouvelle méthode pour ajouter avec image
    @PostMapping("/ajout-avec-image")
    public Optional<Propriete> addProprieteWithImage(
            @RequestPart("propriete") Propriete propriete,
            @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {

        if (image != null && !image.isEmpty()) {
            String imagePath = uploadImage(image);
            propriete.setImagePath(imagePath);
        }

        return Optional.of(proprieteRepository.save(propriete));
    }

    @PutMapping("/ajout")
    public Optional<Propriete> editPropriete(@RequestBody Propriete propriete) {
        return Optional.of(proprieteRepository.save(propriete));
    }

    // Nouvelle méthode pour modifier avec image
    @PutMapping("/modifier-avec-image")
    public Optional<Propriete> editProprieteWithImage(
            @RequestPart("propriete") Propriete propriete,
            @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {

        if (image != null && !image.isEmpty()) {
            String imagePath = uploadImage(image);
            propriete.setImagePath(imagePath);
        } else {
            // Conserver l'image existante si pas de nouvelle image
            Propriete existingPropriete = proprieteRepository.findById(propriete.getId()).orElse(null);
            if (existingPropriete != null && existingPropriete.getImagePath() != null) {
                propriete.setImagePath(existingPropriete.getImagePath());
            }
        }

        return Optional.of(proprieteRepository.save(propriete));
    }

    // Méthode pour supprimer
    @DeleteMapping("/supprimer/{id}")
    public void deletePropriete(@PathVariable Long id) {
        proprieteRepository.deleteById(id);
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
