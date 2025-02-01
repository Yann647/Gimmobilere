package com.gestion.immobiliere.Gimmobilere.repository;

import com.gestion.immobiliere.Gimmobilere.constant.StatutReservation;
import com.gestion.immobiliere.Gimmobilere.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByStatutReservation(StatutReservation statutReservation);
}
