package com.gestion.immobiliere.Gimmobilere.model;

import com.gestion.immobiliere.Gimmobilere.constant.StatutReservation;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date dateReservation;

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

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
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
