package com.gestion.immobiliere.Gimmobilere.web.controller;

import com.gestion.immobiliere.Gimmobilere.model.User;
import com.gestion.immobiliere.Gimmobilere.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(value = "user/liste")
    public String listUsers(Model model, org.springframework.security.core.Authentication authentication) {
        model.addAttribute("users", userRepository.findAll());

        boolean connected = authentication != null
                && authentication.isAuthenticated()
                && !(authentication instanceof
                org.springframework.security.authentication.AnonymousAuthenticationToken);

        String role = "";
        if (connected) {
            role = authentication.getAuthorities()
                    .stream()
                    .map(org.springframework.security.core.GrantedAuthority::getAuthority)
                    .findFirst()
                    .orElse("");
        }

        model.addAttribute("connected", connected);
        model.addAttribute("role", role);

        return "user/liste";
    }

    @RequestMapping(value = "user/ajout")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "user/add";
    }

    @RequestMapping(value = "user/edit/{id}")
    public String editUser(@PathVariable long id, Model model) {
        model.addAttribute("user", userRepository.findById(id).orElse(null));
        return "user/edite";
    }

    @RequestMapping(value = "user/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/user/liste";
    }

    @RequestMapping(value = "user/update", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute User user) {

        User existingUser = userRepository.findById(user.getId())
                .orElse(null);

        if (existingUser == null) {
            return "redirect:/user/liste";
        }

        existingUser.setNom(user.getNom());
        existingUser.setPrenom(user.getPrenom());
        existingUser.setTelephone(user.getTelephone());
        existingUser.setTypePieceIdentite(user.getTypePieceIdentite());
        existingUser.setNumeroPieceIdentite(user.getNumeroPieceIdentite());
        existingUser.setEmail(user.getEmail());
        existingUser.setLogin(user.getLogin());
        existingUser.setRole(user.getRole());

        if (user.getPassword() != null
                && !user.getPassword().isBlank()) {
            existingUser.setPassword(
                    passwordEncoder.encode(user.getPassword())
            );
        }

        userRepository.save(existingUser);

        return "redirect:/user/liste";
    }

    @RequestMapping(value = "user/supprimer/{id}")
    public String deleteUser(@PathVariable long id) {
        userRepository.deleteById(id);
        return "redirect:/user/liste";
    }


}
