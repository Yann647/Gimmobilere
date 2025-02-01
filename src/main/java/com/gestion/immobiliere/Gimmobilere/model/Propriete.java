package com.gestion.immobiliere.Gimmobilere.model;

import com.gestion.immobiliere.Gimmobilere.constant.Disponible;
import com.gestion.immobiliere.Gimmobilere.constant.TypePropriete;
import jakarta.persistence.*;

@Entity
public class Propriete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String adresse;
    private double prix;

    @Enumerated(EnumType.STRING)
    private TypePropriete type;

    private String description;

    @Enumerated(EnumType.STRING)
    private Disponible disponible;

    @ManyToOne
    private User proprietaire;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public TypePropriete getType() {
        return type;
    }

    public void setType(TypePropriete type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Disponible getDisponible() {
        return disponible;
    }

    public void setDisponible(Disponible disponible) {
        this.disponible = disponible;
    }

    public User getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(User proprietaire) {
        this.proprietaire = proprietaire;
    }
}
