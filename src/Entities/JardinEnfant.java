/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author ASUS
 */
public class JardinEnfant {
    private int id ; 
    private String nom , description , numTel, adresse , etat;
    private Double tarif ;

    public JardinEnfant() {
    }

    public JardinEnfant(int id, String nom, String description, String numTel, String adresse, String etat, Double tarif) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.numTel = numTel;
        this.adresse = adresse;
        this.etat = etat;
        this.tarif = tarif;
    }

    
    public JardinEnfant(String nom, String description, String numTel, String adresse, String etat, Double tarif) {
        this.nom = nom;
        this.description = description;
        this.numTel = numTel;
        this.adresse = adresse;
        this.etat = etat;
        this.tarif = tarif;
    }

    @Override
    public String toString() {
        return "nom=" + nom + ", description=" + description + ", numTel=" + numTel + ", adresse=" + adresse + ", etat=" + etat + ", tarif=" + tarif + '}';
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Double getTarif() {
        return tarif;
    }

    public void setTarif(Double tarif) {
        this.tarif = tarif;
    }
    
}
