package com.gestion.immobiliere.Gimmobilere.repository;

import com.gestion.immobiliere.Gimmobilere.constant.Disponible;
import com.gestion.immobiliere.Gimmobilere.model.Propriete;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProprieteRepository extends JpaRepository<Propriete, Long> {
    List<Propriete> findByDisponible(Disponible OUI);
}
