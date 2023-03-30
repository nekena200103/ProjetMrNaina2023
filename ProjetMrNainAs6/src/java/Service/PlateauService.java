/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entity.Plateau;
import entity.Type;
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
public class PlateauService {
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
    public void save(Plateau obj)throws Exception{
        ray.save(obj);
    }
    public void update(Plateau obj)throws Exception{
        ray.update(obj);
    }
    public void delete(Plateau obj)throws Exception{
        ray.delete(obj);
    }
    public ArrayList<Plateau> find(Plateau obj)throws Exception{
        ArrayList<Object>list=ray.find(obj);
        ArrayList<Plateau>liste=new ArrayList<Plateau>();
        for (int i = 0; i <list.size(); i++) {
            liste.add((Plateau)list.get(i));
        }
        return liste;
    }
    public ArrayList<Plateau> pagination(String ra,int page,int nbPage)throws Exception{
        ArrayList<Object>list=ray.pagination(ra,page,nbPage);
        ArrayList<Plateau>liste=new ArrayList<Plateau>();
        for (int i = 0; i <list.size(); i++) {
            liste.add((Plateau)list.get(i));
        }
        return liste;
    }
    public String get(int id)throws Exception{
        Plateau t=new Plateau();
        t.setIdplateau(id);
        return ((Plateau)ray.find(t).get(0)).getIntitule_plateau();
    }
}
