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
public class Tuteur extends User {
     private  int id;

  
    private String nom;
  
    private String prenom;
  
    private String sexe;

  
    private Jardin jardin;

    
    private Collection<Remarque>remarques;

}
