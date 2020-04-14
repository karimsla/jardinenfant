/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author karim
 */
public class Abonnement {
    
    
     private int id;

   
    private Date date;

   
    private String type;

    
    private String etat;

  
    private float montant;

    
    private Jardin jardin;

   
   private Remarque remarques;

   
   private Enfant enfant;

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public String getEtat() {
        return etat;
    }

    public float getMontant() {
        return montant;
    }

    public Jardin getJardin() {
        return jardin;
    }

    public Remarque getRemarques() {
        return remarques;
    }

    public Enfant getEnfant() {
        return enfant;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public void setJardin(Jardin jardin) {
        this.jardin = jardin;
    }

    public void setRemarques(Remarque remarques) {
        this.remarques = remarques;
    }

    public void setEnfant(Enfant enfant) {
        this.enfant = enfant;
    }
    
    
    
}
