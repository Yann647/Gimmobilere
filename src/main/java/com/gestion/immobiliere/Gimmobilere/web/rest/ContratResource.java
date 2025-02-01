package com.gestion.immobiliere.Gimmobilere.web.rest;

import com.gestion.immobiliere.Gimmobilere.model.Contrat;
import com.gestion.immobiliere.Gimmobilere.repository.ContratRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/contrats")
public class ContratResource {

    @Autowired
    private ContratRepository contratRepository;

    @GetMapping("/liste")
    public List<Contrat> listContrats() {
        return contratRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Contrat> getContratById(@PathVariable Long id) {
        return contratRepository.findById(id);
    }

    @PostMapping("/ajout")
    public Optional<Contrat> addContrat(@RequestBody Contrat contrat) {
        return Optional.of(contratRepository.save(contrat));
    }

    @PutMapping("/ajout")
    public Optional<Contrat> editContrat(@RequestBody Contrat contrat) {
        return Optional.of(contratRepository.save(contrat));


    }
}
