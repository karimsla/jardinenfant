/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;


import java.util.List;


/**
 *
 * @author ASUS
 */
public interface CrudGeneric<T> {
    
    public T findById(int id );
    public int create(T type);
    public int update(T type);
    public int delet(T type);
    public  List <T> findAll();
    
}
