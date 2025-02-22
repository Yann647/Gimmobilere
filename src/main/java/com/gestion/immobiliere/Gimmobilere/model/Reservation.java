package com.gestion.immobiliere.Gimmobilere.model;

import com.gestion.immobiliere.Gimmobilere.constant.StatutReservation;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateReservation;

    @Enumerated(EnumType.STRING)
    private StatutReservation statutReservation;

    @ManyToOne
    private Propriete propriete;

    @ManyToOne
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(LocalDate dateReservation) {
        this.dateReservation = dateReservation;
    }

    public com.gestion.immobiliere.Gimmobilere.constant.StatutReservation getStatutReservation() {
        return statutReservation;
    }

    public void setStatutReservation(com.gestion.immobiliere.Gimmobilere.constant.StatutReservation statutReservation) {
        statutReservation = statutReservation;
    }

    public Propriete getPropriete() {
        return propriete;
    }

    public void setPropriete(Propriete propriete) {
        this.propriete = propriete;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
