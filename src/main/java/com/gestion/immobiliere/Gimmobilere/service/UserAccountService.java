package com.gestion.immobiliere.Gimmobilere.service;

import com.gestion.immobiliere.Gimmobilere.constant.Role;
import com.gestion.immobiliere.Gimmobilere.model.User;
import com.gestion.immobiliere.Gimmobilere.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserAccountService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserAccountService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User registerClient(User user) {
        if (user.getLogin() == null
                || user.getLogin().isBlank()) {
            throw new IllegalArgumentException(
                    "Le login est obligatoire");
        }

        if (user.getPassword() == null
                || user.getPassword().length() < 12) {
            throw new IllegalArgumentException(
                    "Le mot de passe doit contenir au moins 12 caractères");
        }

        if (userRepository.findByLogin(user.getLogin()).isPresent()) {
            throw new IllegalArgumentException(
                    "Ce login est déjà utilisé");
        }

        if (user.getEmail() == null
                || user.getEmail().isBlank()) {
            throw new IllegalArgumentException(
                    "L'adresse e-mail est obligatoire");
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException(
                    "Cette adresse e-mail est déjà utilisée");
        }

        user.setRole(Role.CLIENT);
        user.setPassword(
                passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

}
