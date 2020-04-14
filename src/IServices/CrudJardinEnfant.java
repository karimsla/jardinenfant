/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.JardinEnfant;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface CrudJardinEnfant extends CrudGeneric<JardinEnfant>{
        public List <JardinEnfant> findByNom(String  nom );
        public List <JardinEnfant> findByNum(String  numTel );
        public List <JardinEnfant> findByEtat(String  etat );
        public List <JardinEnfant> findByTarif(Double  tarif );
                public List <JardinEnfant> findByAdresse(String  adresse );


    
    
}
