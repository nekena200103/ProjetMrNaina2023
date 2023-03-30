/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entity.Logback;
import entity.Objectinsert;
import inter.InterfaceDao;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class LogbackService {
    @Autowired
    @Qualifier("hibernateDao")
    InterfaceDao ray;
    @Qualifier("genericDao")
    InterfaceDao roa;
    public Logback user(String nom,String mdp){
        Logback ra=null;
        System.out.println(nom+mdp);
        try{
            ra=(Logback)(ray.find(new Logback(nom,mdp)).get(0));
        }catch(Exception e){
             
        }
        return ra;
    }
    public ArrayList<Objectinsert> find(Objectinsert obj)throws Exception{
        System.out.println("Raylg "+ray);
        System.out.println("Raylg "+roa);
        ArrayList<Object>list=ray.find(obj);
        ArrayList<Objectinsert>liste=new ArrayList<>();
        for (int i = 0; i <list.size(); i++) {
            liste.add((Objectinsert)list.get(i));
        }
        return liste;
    }
}
