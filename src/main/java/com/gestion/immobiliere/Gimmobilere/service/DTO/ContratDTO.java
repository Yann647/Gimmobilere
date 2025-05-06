package com.gestion.immobiliere.Gimmobilere.service.DTO;

import com.gestion.immobiliere.Gimmobilere.constant.TypeContrat;

import java.time.LocalDate;

public class ContratDTO {

    private long id;
    private LocalDate dateContrat;
    private double montant;
    private TypeContrat typeContrat;
    private Long proprieteId;
    private String proprieteAdresse;
    private Long reservationId;
    private Long userId;
    private String userNom;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDateContrat() {
        return dateContrat;
    }

    public void setDateContrat(LocalDate dateContrat) {
        this.dateContrat = dateContrat;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public TypeContrat getTypeContrat() {
        return typeContrat;
    }

    public void setTypeContrat(TypeContrat typeContrat) {
        this.typeContrat = typeContrat;
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

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
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
