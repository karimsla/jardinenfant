/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.JardinEnfant;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;

/**
 *
 * @author ASUS
 */
public class RaedTest {
    public static void main(String[] args)  {
        try {
            CrudJardinEnfantImpl crrud = new CrudJardinEnfantImpl();
            JardinEnfant jardin = new JardinEnfant();

//            jardin.setAdresse("test");
//            jardin.setDescription("test");
//            jardin.setEtat("test");
//            jardin.setNom("test");
//            jardin.setTarif(120.2);
//            jardin.setNumTel("2154843123");
//            System.out.println(crrud.create(jardin)); 
            
            int x= crrud.delet(45);
            
            System.out.println(x);
        } catch (SQLException ex) {
ex.printStackTrace();        }
        

    } 

    
    
}
