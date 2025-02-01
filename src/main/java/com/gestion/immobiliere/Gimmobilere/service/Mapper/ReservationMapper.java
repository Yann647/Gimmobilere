package com.gestion.immobiliere.Gimmobilere.service.Mapper;

import com.gestion.immobiliere.Gimmobilere.model.Propriete;
import com.gestion.immobiliere.Gimmobilere.model.Reservation;
import com.gestion.immobiliere.Gimmobilere.model.User;
import com.gestion.immobiliere.Gimmobilere.service.DTO.ReservationDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ReservationMapper {

    public Reservation toEntity(ReservationDTO dto) {
        if (dto == null) return null;

        Reservation entity = new Reservation();
        entity.setId(dto.getId());
        entity.setDateReservation(dto.getDateReservation());
        entity.setStatutReservation(dto.getStatutReservation());

        if (dto.getProprieteId() != null) {
            Propriete propriete = new Propriete();
            propriete.setId(dto.getProprieteId());
            entity.setPropriete(propriete);
        }

        if (dto.getUserId() != null) {
            User user = new User();
            user.setId(dto.getUserId());
            entity.setUser(user);
        }

        return entity;

    }

    public ReservationDTO toDto(Reservation entity) {
        if (entity == null) return null;

        ReservationDTO dto = new ReservationDTO();
        dto.setId(entity.getId());
        dto.setDateReservation(entity.getDateReservation());
        dto.setStatutReservation(entity.getStatutReservation());

        if (entity.getPropriete() != null) {
            dto.setProprieteId(entity.getPropriete().getId());
            dto.setProprieteAdresse(entity.getPropriete().getAdresse());
        }

        if (entity.getUser() != null) {
            dto.setUserId(entity.getUser().getId());
            dto.setUserNom(entity.getUser().getNom());
        }

        return dto;
    }

    public List<Reservation> toEntity(List<ReservationDTO> dtoList) {
        return dtoList.stream().filter(Objects::nonNull).map(this::toEntity).collect(Collectors.toList());
    }

    public List<ReservationDTO> toDto(List<Reservation> entityList) {
        return entityList.stream().filter(Objects::nonNull).map(this::toDto).collect(Collectors.toList());
    }

}
