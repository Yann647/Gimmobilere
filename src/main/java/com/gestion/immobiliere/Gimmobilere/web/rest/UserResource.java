package com.gestion.immobiliere.Gimmobilere.web.rest;

import com.gestion.immobiliere.Gimmobilere.model.User;
import com.gestion.immobiliere.Gimmobilere.repository.UserRepository;
import com.gestion.immobiliere.Gimmobilere.service.DTO.UserResponseDTO;
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
    public List<UserResponseDTO> listUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserResponseDTO(
                        user.getId(),
                        user.getNom(),
                        user.getPrenom(),
                        user.getEmail(),
                        user.getLogin(),
                        user.getRole() != null
                                ? user.getRole().name()
                                : null
                ))
                .toList();
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
