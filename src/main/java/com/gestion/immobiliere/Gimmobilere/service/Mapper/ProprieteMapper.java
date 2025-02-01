package com.gestion.immobiliere.Gimmobilere.service.Mapper;

import com.gestion.immobiliere.Gimmobilere.model.Propriete;
import com.gestion.immobiliere.Gimmobilere.service.DTO.ProprieteDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProprieteMapper {

    public Propriete toEntity(ProprieteDTO dto) {
        if (dto == null) return null;

        Propriete entity = new Propriete();
        entity.setId(dto.getId());
        entity.setAdresse(dto.getAdresse());
        entity.setPrix(dto.getPrix());
        entity.setDescription(dto.getDescription());
        entity.setType(dto.getTypePropriete());
        entity.setDisponible(dto.getDisponible());

        return entity;

    }

    public ProprieteDTO toDto(Propriete entity) {
        if (entity == null) return null;

        ProprieteDTO dto = new ProprieteDTO();
        dto.setId(entity.getId());
        dto.setAdresse(entity.getAdresse());
        dto.setPrix(entity.getPrix());
        dto.setDescription(entity.getDescription());
        dto.setTypePropriete(entity.getType());
        dto.setDisponible(entity.getDisponible());

        return dto;

    }

    public List<Propriete> toEntity(List<ProprieteDTO> dtoList) {
        return dtoList.stream().filter(Objects::nonNull).map(this::toEntity).collect(Collectors.toList());
    }

    public List<ProprieteDTO> toDto(List<Propriete> entityList) {
        return entityList.stream().filter(Objects::nonNull).map(this::toDto).collect(Collectors.toList());
    }

}
