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
public class Parents extends User {
    private int id;

   
    private String nom;

   
    private String prenom;

    
    private String numtel;

    private String adresse;

    
    private String sexe;

      private Collection<Enfant> enfants;

    
    private Collection<Messages> messages;

    
    private  Collection<Reclamation>reclamations;

}
