/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import java.util.Collection;

import Entities.Paiement;


/**
 *
 * @author ASUS
 */
public interface CrudPayment extends CrudGeneric<Paiement>{
            public Collection <Paiement> findByMontant(Double  montant );

}
