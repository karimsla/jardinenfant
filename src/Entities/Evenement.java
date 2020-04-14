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


    
    private JardinEnfant jardin;
   
   private  Collection<Participer> participation;

    public Evenement(String titre, Date date, String description, String image) {
        this.titre = titre;
        this.date = date;
        this.description = description;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public JardinEnfant getJardin() {
        return jardin;
    }

    public void setJardin(JardinEnfant jardin) {
        this.jardin = jardin;
    }

    public Collection<Participer> getParticipation() {
        return participation;
    }

    public void setParticipation(Collection<Participer> participation) {
        this.participation = participation;
    }
    
    
}
