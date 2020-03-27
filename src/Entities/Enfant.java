/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.util.Collection;

/**
 *
 * @author karim
 */
public class Enfant {
    
    
    private int id;

   
    private String nom;

    
    
    private String prenom;

   
    private Date datenaiss;

    
    private String sexe;


    
    private Collection<Abonnement> $abonnements;

   
    private Parents parent;



   
    private Collection<Participer> participation;

    
    private  Collection<PartActivite> participerActivite;

    
}
