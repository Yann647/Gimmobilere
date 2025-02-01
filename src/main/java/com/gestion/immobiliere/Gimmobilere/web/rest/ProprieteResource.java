package com.gestion.immobiliere.Gimmobilere.web.rest;

import com.gestion.immobiliere.Gimmobilere.model.Propriete;
import com.gestion.immobiliere.Gimmobilere.repository.ProprieteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/proprietes")
public class ProprieteResource {

    @Autowired
    private ProprieteRepository proprieteRepository;

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

    @PutMapping("/ajout")
    public Optional<Propriete> editPropriete(@RequestBody Propriete propriete) {
        return Optional.of(proprieteRepository.save(propriete));
    }

}
