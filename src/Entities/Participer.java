/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author karim
 */
public class Participer {
     private int id;


    private Enfant enfant;
    
    private Evenement evenement;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Enfant getEnfant() {
        return enfant;
    }

    public void setEnfant(Enfant enfant) {
        this.enfant = enfant;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    @Override
    public String toString() {
        return "Participer{" + "id=" + id + ", enfant=" + enfant + ", evenement=" + evenement + '}';
    }
    
    
    
    
}

