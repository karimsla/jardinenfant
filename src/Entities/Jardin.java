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
}
