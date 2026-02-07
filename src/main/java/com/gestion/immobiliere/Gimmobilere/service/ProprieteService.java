package com.gestion.immobiliere.Gimmobilere.service;

import com.gestion.immobiliere.Gimmobilere.model.Propriete;
import com.gestion.immobiliere.Gimmobilere.repository.ProprieteRepository;
import com.gestion.immobiliere.Gimmobilere.service.DTO.ProprieteDTO;
import com.gestion.immobiliere.Gimmobilere.service.Mapper.ProprieteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
@Transactional
public class ProprieteService {

    private final String UPLOAD_DIR = "uploads/";

    @Autowired
    private ProprieteMapper proprieteMapper;

    @Autowired
    private ProprieteRepository proprieteRepository;

    @Transactional
    public ProprieteDTO save(ProprieteDTO proprieteDTO) {
        Propriete propriete = proprieteMapper.toEntity(proprieteDTO);
        return proprieteMapper.toDto(proprieteRepository.save(propriete));
    }

    @Transactional
    public ProprieteDTO saveWithImage(ProprieteDTO proprieteDTO, MultipartFile image) throws IOException {
        // Convertir DTO en entité
        Propriete propriete = proprieteMapper.toEntity(proprieteDTO);

        // Gérer l'upload de l'image
        if (image != null && !image.isEmpty()) {
            String imagePath = uploadImage(image);
            propriete.setImagePath(imagePath);
        }

        // Sauvegarder l'entité
        Propriete savedPropriete = proprieteRepository.save(propriete);

        // Retourner le DTO
        return proprieteMapper.toDto(savedPropriete);
    }

    public ProprieteDTO findById(Long id) {
        return proprieteRepository.findById(id)
                .map(proprieteMapper::toDto)
                .orElse(null);
    }

    private String uploadImage(MultipartFile image) throws IOException {
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR + fileName);
        Files.write(filePath, image.getBytes());

        return fileName;
    }

}
