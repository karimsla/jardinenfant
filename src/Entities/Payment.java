/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class Payment {
    private int id ; 
    private Date date; 
    private Double montant;
    public int prov;
    
    private JardinEnfant jardinEnfants;

    public Payment(Date date, Double montant) {
        this.date = date;
        this.montant = montant;
    }
     public Payment(Date date, Double montant,int prov) {
        this.date = date;
        this.montant = montant;
        this.prov=prov;
    }

    public int getProv() {
        return prov;
    }

    public void setProv(int prov) {
        this.prov = prov;
    }

    public Payment(Date date, Double montant, JardinEnfant jardinEnfants) {
        this.date = date;
        this.montant = montant;
        this.jardinEnfants = jardinEnfants;
    }
    
    
    

    @Override
    public String toString() {
        
        return "Payment{" + "id=" + id + ", date=" + date + ", montant=" + montant + "jardin" + jardinEnfants.toString();
    }

    public Payment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public JardinEnfant getJardinEnfants() {
        return jardinEnfants;
    }

    public void setJardinEnfants(JardinEnfant jardinEnfants) {
        this.jardinEnfants = jardinEnfants;
    }

    
   
    
}
