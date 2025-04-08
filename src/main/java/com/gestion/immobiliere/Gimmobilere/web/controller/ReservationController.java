package com.gestion.immobiliere.Gimmobilere.web.controller;

import com.gestion.immobiliere.Gimmobilere.model.Reservation;
import com.gestion.immobiliere.Gimmobilere.repository.ProprieteRepository;
import com.gestion.immobiliere.Gimmobilere.repository.ReservationRepository;
import com.gestion.immobiliere.Gimmobilere.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReservationController {

    private final ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProprieteRepository proprieteRepository;

    public ReservationController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @RequestMapping(value = "reservation/liste")
    public String listReservations(Model model) {
        model.addAttribute("reservations", reservationRepository.findAll());
        return "reservation/liste";
    }

    @RequestMapping(value = "reservation/ajout")
    public String addReservation(Model model) {
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("proprietes", proprieteRepository.findAll());
        return "reservation/add";
    }

    @RequestMapping(value = "reservation/edit/{id}")
    public String editReservation(@PathVariable long id, Model model) {
        model.addAttribute("reservation", reservationRepository.findById(id).orElse(null));
        return "reservation/edite";
    }

    @RequestMapping(value = "reservation/save", method = RequestMethod.POST)
    public String saveReservation(@ModelAttribute Reservation reservation) {
        reservationRepository.save(reservation);
        return "redirect:/reservation/liste";
    }

    @RequestMapping(value = "reservation/update", method = RequestMethod.POST)
    public String updateReservation(@ModelAttribute Reservation reservation) {
        reservationRepository.save(reservation);
        return "redirect:/reservation/liste";
    }

    @RequestMapping(value = "reservation/supprimer/{id}")
    public String deleteReservation(@PathVariable long id) {
        reservationRepository.deleteById(id);
        return "redirect:/reservation/liste";
    }

}
