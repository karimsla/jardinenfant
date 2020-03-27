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
public class Evenement {
    
     private int id;

    
    private String titre;

    
    private Date date;

  
    private String description;

   
    private String image;


   
    private Categorie categorie;


    
    private Jardin jardin;
   
   private  Collection<Participer> participation;
    
    
}
