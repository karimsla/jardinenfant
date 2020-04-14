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

public class User {
    
    private int id;
    
    private Collection<Messages> messages;
    
    private String username;
    
    private String email;
    private boolean enabled;
    private String password;
 
    
}
