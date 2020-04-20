/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Jardin;

import java.util.List;

/**
 *
 * @author ASUS
 */
public interface CrudJardinEnfant extends CrudGeneric<Jardin> {
        public List <Jardin> findByNom(String nom);
        public List <Jardin> findByNum(String numTel);
        public List <Jardin> findByEtat(String etat);
        public List <Jardin> findByTarif(Double tarif);
                public List <Jardin> findByAdresse(String adresse);
        public List <Jardin> findByEnAttente();
        public int updateStat(int id);


    
    
}
