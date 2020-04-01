/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author FERID
 */
public class AbonEnf {
    
    private String nom;
    private String prenom;
    private String etat;
    private Date datenaiss;
    private String type;
    private int montant;
    private Date date;

    public AbonEnf(String nom, String prenom, String etat, Date datenaiss,String type,int montant,Date date) {
        this.nom = nom;
        this.prenom = prenom;
        this.etat = etat;
        this.datenaiss = datenaiss;
        this.type=type;
        this.montant=montant;
        this.date=date;
    }

    public AbonEnf() {
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEtat() {
        return etat;
    }

    public Date getDatenaiss() {
        return datenaiss;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setDatenaiss(Date datenaiss) {
        this.datenaiss = datenaiss;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
    
    
}
