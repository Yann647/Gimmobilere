package com.gestion.immobiliere.Gimmobilere.web.rest;

import com.gestion.immobiliere.Gimmobilere.constant.Role;
import com.gestion.immobiliere.Gimmobilere.model.User;
import com.gestion.immobiliere.Gimmobilere.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthResource {

    @Autowired
    private UserRepository userRepository;

    // INSCRIPTION API
    @PostMapping("/register")
    public Object register(@RequestBody User user) {

        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isPresent()) {
            return "Email déjà utilisé";
        }

        user.setRole(Role.CLIENT);

        return userRepository.save(user);
    }

    // CONNEXION API
    @PostMapping("/login")
    public Object login(@RequestBody User user) {

        User loggedUser =
                userRepository.findByLoginAndPassword(
                        user.getLogin(),
                        user.getPassword()
                );

        if (loggedUser != null) {
            return loggedUser;
        } else {
            return "Identifiants invalides";
        }
    }

}
