package com.gestion.immobiliere.Gimmobilere.web.rest;

import com.gestion.immobiliere.Gimmobilere.constant.Role;
import com.gestion.immobiliere.Gimmobilere.model.User;
import com.gestion.immobiliere.Gimmobilere.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthResource {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public AuthResource(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // INSCRIPTION API
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {

        // Vérifier les informations obligatoires
        if (user.getEmail() == null || user.getEmail().isBlank()
                || user.getLogin() == null || user.getLogin().isBlank()
                || user.getPassword() == null
                || user.getPassword().length() < 12) {

            return ResponseEntity.badRequest().body(
                    Map.of("message",
                            "Renseignez l'email et l'identifiant. "
                                    + "Le mot de passe doit contenir "
                                    + "au moins 12 caractères.")
            );
        }

        // Vérifier si l'email est déjà utilisé
        Optional<User> existingEmail =
                userRepository.findByEmail(user.getEmail());

        if (existingEmail.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    Map.of("message", "Email déjà utilisé.")
            );
        }

        // Vérifier si le login est déjà utilisé
        if (userRepository.findByLogin(user.getLogin()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    Map.of("message", "Identifiant déjà utilisé.")
            );
        }

        // Ne jamais laisser le client choisir son rôle ou son ID
        user.setRole(Role.CLIENT);

        // Enregistrer le hash BCrypt, jamais le mot de passe brut
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                Map.of("message", "Inscription réussie.")
        );
    }

    // CONNEXION API
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {

        if (user.getLogin() == null || user.getLogin().isBlank()
                || user.getPassword() == null
                || user.getPassword().isBlank()) {

            return ResponseEntity.badRequest().body(
                    Map.of("message",
                            "Identifiant et mot de passe obligatoires.")
            );
        }

        // Rechercher le compte à partir du login
        Optional<User> optionalUser =
                userRepository.findByLogin(user.getLogin());

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    Map.of("message", "Identifiants invalides.")
            );
        }

        User loggedUser = optionalUser.get();

        // Comparer le mot de passe saisi avec le hash BCrypt
        if (!passwordEncoder.matches(
                user.getPassword(),
                loggedUser.getPassword())) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    Map.of("message", "Identifiants invalides.")
            );
        }

        // Ne pas renvoyer l'entité User ni le hash du mot de passe
        return ResponseEntity.ok(
                Map.of(
                        "message", "Connexion réussie.",
                        "id", loggedUser.getId(),
                        "nom", loggedUser.getNom() == null
                                ? "" : loggedUser.getNom(),
                        "prenom", loggedUser.getPrenom() == null
                                ? "" : loggedUser.getPrenom(),
                        "login", loggedUser.getLogin(),
                        "role", loggedUser.getRole().name()
                )
        );
    }

}
