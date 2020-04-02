/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author karim
 */
public class ConnexionBD {
    private String url = "jdbc:mysql://127.0.0.1:3306/jardinenfant?autoReconnect=true&serverTimezone=UTC";
    private String login = "root";
    private String pwd = "";
    private Connection cnx;
    private static ConnexionBD instance;

    public Connection getCnx() {
        return cnx;
    }

    private ConnexionBD() {
        try {
            cnx = DriverManager.getConnection(url, login, pwd);
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ConnexionBD getInstance() {

        if (instance == null) {
            instance = new ConnexionBD();
        }
        return instance;
    }
}
