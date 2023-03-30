/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entity.Action;
import entity.Personne;
import inter.InterfaceDao;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author Nekena
 */
@Component
public class PersonneService {
     @Autowired
    @Qualifier("hibernateDao")
    InterfaceDao ray;
     @Qualifier("genericDao")
    InterfaceDao roa;
     public ArrayList<Personne> findAll()throws Exception{
        System.out.println("Ray "+ray );
        Personne pers=new Personne(-1,null);
        ArrayList<Object>list=ray.find(pers); 
        ArrayList<Personne>liste=new ArrayList<>();
        for (int i = 0; i <list.size(); i++) {
            liste.add((Personne)list.get(i));
        }
        return liste;
    }
}
