package com.gestion.immobiliere.Gimmobilere.service.Mapper;

import com.gestion.immobiliere.Gimmobilere.model.Contrat;
import com.gestion.immobiliere.Gimmobilere.model.Propriete;
import com.gestion.immobiliere.Gimmobilere.model.Reservation;
import com.gestion.immobiliere.Gimmobilere.model.User;
import com.gestion.immobiliere.Gimmobilere.service.DTO.ContratDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ContratMapper {

    public Contrat toEntity(ContratDTO dto) {
        if (dto == null) return null;

        Contrat entity = new Contrat();
        entity.setId(dto.getId());
        entity.setDateDebut(dto.getDateContrat());
        entity.setMontant(dto.getMontant());
        entity.setTypeContrat(dto.getTypeContrat());

        if (dto.getProprieteId() != null) {
            Propriete propriete = new Propriete();
            propriete.setId(dto.getProprieteId());
            entity.setPropriete(propriete);
        }

        if (dto.getReservationId() != null) {
            Reservation reservation = new Reservation();
            reservation.setId(dto.getReservationId());
            entity.setReservation(reservation);
        }

        if (dto.getUserId() != null) {
            User user = new User();
            user.setId(dto.getUserId());
            entity.setUser(user);
        }

        return entity;


    }

    public ContratDTO toDto(Contrat entity){
        if (entity == null) return null;

        ContratDTO dto = new ContratDTO();
        dto.setId(entity.getId());
        dto.setDateContrat(entity.getDateDebut());
        dto.setMontant(entity.getMontant());
        dto.setTypeContrat(entity.getTypeContrat());

        if (entity.getPropriete() != null) {
            dto.setProprieteId(entity.getPropriete().getId());
            dto.setProprieteAdresse(entity.getPropriete().getAdresse());
        }

        if (entity.getReservation() != null) {
            dto.setReservationId(entity.getReservation().getId());
        }

        if (entity.getUser() != null) {
            dto.setUserId(entity.getUser().getId());
            dto.setUserNom(entity.getUser().getNom());
        }

        return dto;

    }

    public List<Contrat> toEntity(List<ContratDTO> dtoList) {
        return dtoList.stream().filter(Objects::nonNull).map(this::toEntity).collect(Collectors.toList());
    }

    public List<ContratDTO> toDto(List<Contrat> entityList) {
        return entityList.stream().filter(Objects::nonNull).map(this::toDto).collect(Collectors.toList());
    }

}
