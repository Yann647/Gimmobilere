package com.gestion.immobiliere.Gimmobilere.service;

import com.gestion.immobiliere.Gimmobilere.model.Propriete;
import com.gestion.immobiliere.Gimmobilere.repository.ProprieteRepository;
import com.gestion.immobiliere.Gimmobilere.service.DTO.ProprieteDTO;
import com.gestion.immobiliere.Gimmobilere.service.Mapper.ProprieteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProprieteService {

    @Autowired
    private ProprieteMapper proprieteMapper;

    @Autowired
    private ProprieteRepository proprieteRepository;

    @Transactional
    public ProprieteDTO save(ProprieteDTO proprieteDTO) {
        Propriete propriete = proprieteMapper.toEntity(proprieteDTO);
        return proprieteMapper.toDto(proprieteRepository.save(propriete));
    }

    public ProprieteDTO findById(Long id) {
        return proprieteRepository.findById(id)
                .map(proprieteMapper::toDto)
                .orElse(null);
    }

}
