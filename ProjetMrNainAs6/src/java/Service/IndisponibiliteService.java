/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entity.Indisponibilite;
import entity.Plateau;
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
public class IndisponibiliteService {
    @Autowired
    @Qualifier("hibernateDao")
    InterfaceDao ray;
     @Qualifier("genericDao")
    InterfaceDao roa;
    public void setRay(InterfaceDao ray){
        this.ray=ray;
    }
    public void setRoa(InterfaceDao roa){
        this.roa=roa;
    }
    public void save(Indisponibilite obj)throws Exception{
        ray.save(obj);
    }
    public void update(Indisponibilite obj)throws Exception{
        ray.update(obj);
    }
    public void delete(Indisponibilite obj)throws Exception{
        ray.delete(obj);
    }
    public ArrayList<Indisponibilite> find(Indisponibilite obj)throws Exception{
        ArrayList<Object>list=ray.pagination(" from Indisponibilite where heuredebut>now() and heurefin>now() ", 0, 0);
        ArrayList<Indisponibilite>liste=new ArrayList<Indisponibilite>();
        for (int i = 0; i <list.size(); i++) {
            liste.add((Indisponibilite)list.get(i));
        }
        return liste;
    }
    public ArrayList<Indisponibilite> find2(Indisponibilite obj)throws Exception{
        ArrayList<Object>list=ray.find(obj);
        ArrayList<Indisponibilite>liste=new ArrayList<Indisponibilite>();
        for (int i = 0; i <list.size(); i++) {
            liste.add((Indisponibilite)list.get(i));
        }
        return liste;
    }
}
