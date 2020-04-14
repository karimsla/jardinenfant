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
public class Remarque {
    private int id;

   
    private String description;

  
    private Date date;

   
    private Abonnement abonnement;

   
    private Tuteur tuteur;
}
