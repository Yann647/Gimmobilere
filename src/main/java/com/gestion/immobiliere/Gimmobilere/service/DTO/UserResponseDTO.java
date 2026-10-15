package com.gestion.immobiliere.Gimmobilere.service.DTO;

public record UserResponseDTO (
        Long id,
        String nom,
        String prenom,
        String email,
        String login,
        String role
) {
}
