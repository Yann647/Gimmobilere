package com.gestion.immobiliere.Gimmobilere.web.rest;

import com.gestion.immobiliere.Gimmobilere.model.Reservation;
import com.gestion.immobiliere.Gimmobilere.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/reservations")
public class ReservationResource {

    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping("/liste")
    public List<Reservation> listReservations() {
        return reservationRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Reservation> getReservationById(@PathVariable Long id) {
        return reservationRepository.findById(id);
    }

    @PostMapping("/ajout")
    public Optional<Reservation> addReservation(@RequestBody Reservation reservation) {
        return Optional.of(reservationRepository.save(reservation));
    }

    @PutMapping("/ajout")
    public Optional<Reservation> editReservation(@RequestBody Reservation reservation) {
        return Optional.of(reservationRepository.save(reservation));
    }

}
