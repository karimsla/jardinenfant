/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Collection;

/**
 *
 * @author karim
 */
public class Jardin {
    
    private int id;

  
    private String name;

    
    private String description;
 
    
    
    private String numtel;

   
    
    private float tarif;


  
    private String adresse;
  
    
    
    private String etat;

   
   private  Collection<Abonnement> abonnements;

   
   private Responsable responsable;

   
    private  Collection<Paiement> paiements;

   
    private  Collection<Messages> messages;

    
    private  Collection<Club> clubs;

   
   private  Collection<Tuteur> tureurs;
   
    private  Collection<Evenement >evenements;

    
    private  Collection<Chauffeur> chauffeurs;

    public Jardin() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getNumtel() {
        return numtel;
    }

    public float getTarif() {
        return tarif;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getEtat() {
        return etat;
    }

    public Collection<Abonnement> getAbonnements() {
        return abonnements;
    }

    public Responsable getResponsable() {
        return responsable;
    }

    public Collection<Paiement> getPaiements() {
        return paiements;
    }

    public Collection<Messages> getMessages() {
        return messages;
    }

    public Collection<Club> getClubs() {
        return clubs;
    }

    public Collection<Tuteur> getTureurs() {
        return tureurs;
    }

    public Collection<Evenement> getEvenements() {
        return evenements;
    }

    public Collection<Chauffeur> getChauffeurs() {
        return chauffeurs;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    public void setTarif(float tarif) {
        this.tarif = tarif;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setAbonnements(Collection<Abonnement> abonnements) {
        this.abonnements = abonnements;
    }

    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }

    public void setPaiements(Collection<Paiement> paiements) {
        this.paiements = paiements;
    }

    public void setMessages(Collection<Messages> messages) {
        this.messages = messages;
    }

    public void setClubs(Collection<Club> clubs) {
        this.clubs = clubs;
    }

    public void setTureurs(Collection<Tuteur> tureurs) {
        this.tureurs = tureurs;
    }

    public void setEvenements(Collection<Evenement> evenements) {
        this.evenements = evenements;
    }

    public void setChauffeurs(Collection<Chauffeur> chauffeurs) {
        this.chauffeurs = chauffeurs;
    }
    
    
    
    
    
}
