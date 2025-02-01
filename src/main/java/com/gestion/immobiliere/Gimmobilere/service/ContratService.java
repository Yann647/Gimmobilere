package com.gestion.immobiliere.Gimmobilere.service;

import com.gestion.immobiliere.Gimmobilere.model.Contrat;
import com.gestion.immobiliere.Gimmobilere.repository.ContratRepository;
import com.gestion.immobiliere.Gimmobilere.service.DTO.ContratDTO;
import com.gestion.immobiliere.Gimmobilere.service.Mapper.ContratMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContratService {

    @Autowired
    private ContratMapper contratMapper;

    @Autowired
    private ContratRepository contratRepository;

    @Transactional
    public ContratDTO save(ContratDTO contratDTO) {
        Contrat contrat = contratMapper.toEntity(contratDTO);
        return contratMapper.toDto(contratRepository.save(contrat));
    }

    public ContratDTO findById(Long id) {
        return contratRepository.findById(id)
                .map(contratMapper::toDto)
                .orElse(null);
    }

}
