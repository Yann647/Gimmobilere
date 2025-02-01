package com.gestion.immobiliere.Gimmobilere.service.DTO;

import com.gestion.immobiliere.Gimmobilere.constant.Disponible;
import com.gestion.immobiliere.Gimmobilere.constant.TypePropriete;

public class ProprieteDTO {

    private long id;
    private String adresse;
    private double prix;
    private String description;
    private TypePropriete typePropriete;
    private Disponible disponible;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypePropriete getTypePropriete() {
        return typePropriete;
    }

    public void setTypePropriete(TypePropriete typePropriete) {
        this.typePropriete = typePropriete;
    }

    public Disponible getDisponible() {
        return disponible;
    }

    public void setDisponible(Disponible disponible) {
        this.disponible = disponible;
    }
}
