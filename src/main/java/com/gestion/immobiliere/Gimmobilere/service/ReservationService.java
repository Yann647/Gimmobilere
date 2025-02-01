package com.gestion.immobiliere.Gimmobilere.service;

import com.gestion.immobiliere.Gimmobilere.model.Reservation;
import com.gestion.immobiliere.Gimmobilere.repository.ReservationRepository;
import com.gestion.immobiliere.Gimmobilere.service.DTO.ReservationDTO;
import com.gestion.immobiliere.Gimmobilere.service.Mapper.ReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class ReservationService {

    @Autowired
    private ReservationMapper reservationMapper;

    @Autowired
    private ReservationRepository reservationRepository;

    @Transactional
    public ReservationDTO save(ReservationDTO reservationDTO) {
        Reservation reservation = reservationMapper.toEntity(reservationDTO);
        return reservationMapper.toDto(reservationRepository.save(reservation));
    }

    public ReservationDTO findById(Long id) {
        return reservationRepository.findById(id)
                .map(reservationMapper::toDto)
                .orElse(null);
    }

}
