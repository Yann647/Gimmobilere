package com.gestion.immobiliere.Gimmobilere.service.DTO;

import com.gestion.immobiliere.Gimmobilere.constant.StatutReservation;

import java.time.LocalDate;

public class ReservationDTO {

    private long id;
    private LocalDate dateReservation;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private StatutReservation statutReservation;
    private Long proprieteId;
    private String proprieteAdresse;
    private Long userId;
    private String userNom;

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

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public StatutReservation getStatutReservation() {
        return statutReservation;
    }

    public void setStatutReservation(StatutReservation statutReservation) {
        this.statutReservation = statutReservation;
    }

    public Long getProprieteId() {
        return proprieteId;
    }

    public void setProprieteId(Long proprieteId) {
        this.proprieteId = proprieteId;
    }

    public String getProprieteAdresse() {
        return proprieteAdresse;
    }

    public void setProprieteAdresse(String proprieteAdresse) {
        this.proprieteAdresse = proprieteAdresse;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserNom() {
        return userNom;
    }

    public void setUserNom(String userNom) {
        this.userNom = userNom;
    }
}
