/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.util.Collection;
import java.util.Objects;


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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeact() {
        return typeact;
    }

    public void setTypeact(String typeact) {
        this.typeact = typeact;
    }

    public String getDetailles() {
        return detailles;
    }

    public void setDetailles(String detailles) {
        this.detailles = detailles;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Collection<PartActivite> getParticipation() {
        return participation;
    }

    public void setParticipation(Collection<PartActivite> participation) {
        this.participation = participation;
    }

    public Activite() {
    }

    public Activite(int id, String typeact, String detailles, String photo, Date Date, Date dateDebut, Date dateFin, Date dateCreation, Club club, Collection<PartActivite> participation) {
        this.id = id;
        this.typeact = typeact;
        this.detailles = detailles;
        this.photo = photo;
        this.Date = Date;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.dateCreation = dateCreation;
        this.club = club;
        this.participation = participation;
    }

    @Override
    public String toString() {
        return "Activite{" + "id=" + id + ", typeact=" + typeact + ", detailles=" + detailles + ", photo=" + photo + ", Date=" + Date + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", dateCreation=" + dateCreation + ", club=" + club + ", participation=" + participation + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Activite other = (Activite) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.typeact, other.typeact)) {
            return false;
        }
        if (!Objects.equals(this.detailles, other.detailles)) {
            return false;
        }
        if (!Objects.equals(this.photo, other.photo)) {
            return false;
        }
        if (!Objects.equals(this.Date, other.Date)) {
            return false;
        }
        if (!Objects.equals(this.dateDebut, other.dateDebut)) {
            return false;
        }
        if (!Objects.equals(this.dateFin, other.dateFin)) {
            return false;
        }
        if (!Objects.equals(this.dateCreation, other.dateCreation)) {
            return false;
        }
        if (!Objects.equals(this.club, other.club)) {
            return false;
        }
        if (!Objects.equals(this.participation, other.participation)) {
            return false;
        }
        return true;
    }
    
    
   
   
    
}
