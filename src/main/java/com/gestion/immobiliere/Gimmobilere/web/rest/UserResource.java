package com.gestion.immobiliere.Gimmobilere.web.rest;

import com.gestion.immobiliere.Gimmobilere.model.User;
import com.gestion.immobiliere.Gimmobilere.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserResource {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/liste")
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    @PostMapping("/ajout")
    public Optional<User> addUser(@RequestBody User user) {
        return Optional.of(userRepository.save(user));
    }

    @PutMapping("/ajout")
    public Optional<User> editUser(@RequestBody User user) {
        return Optional.of(userRepository.save(user));
    }

}
