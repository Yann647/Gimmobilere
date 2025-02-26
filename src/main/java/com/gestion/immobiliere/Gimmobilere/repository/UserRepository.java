package com.gestion.immobiliere.Gimmobilere.repository;

import com.gestion.immobiliere.Gimmobilere.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    User findByLoginAndPassword(String login, String password);


}
