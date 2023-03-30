/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entity.Acteur;
import inter.InterfaceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author caeyla
 */
@Component
public class ActeurService {
     @Autowired
    @Qualifier("hibernateDao")
    InterfaceDao ray;
     @Qualifier("genericDao")
    InterfaceDao roa;
     public Acteur user(String nom,String mdp){
        Acteur ra=null;
        try{
            ra=(Acteur)(ray.find(new Acteur(nom,mdp)).get(0));
        }catch(Exception e){
             
        }
        return ra;
    }
}
