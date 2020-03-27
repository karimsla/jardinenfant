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
public class Activite {
    
     private int id;

  
    private String typeact;

  
    private String detailles;


    
    private String photo;

   

    private Date Date;


   
    private Date dateDebut;

   
    private Date dateFin;
   
    private Date dateCreation;
   
    private Club club;

    
    private Collection<PartActivite> participation;
    
}
