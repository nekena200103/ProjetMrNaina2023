package Service;


import entity.*;
import inter.InterfaceDao;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author USER
 */
@Component
public class TypeService {
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
    public void save(Type obj)throws Exception{
        ray.save(obj);
    }
    public void update(Type obj)throws Exception{
        ray.update(obj);
    }
    public void delete(Type obj)throws Exception{
        ray.delete(obj);
    }
    public ArrayList<Type> find(Type obj)throws Exception{
        ArrayList<Object>list=ray.find(obj);
        ArrayList<Type>liste=new ArrayList<Type>();
        for (int i = 0; i <list.size(); i++) {
            liste.add((Type)list.get(i));
        }
        return liste;
    }
    public ArrayList<Type> pagination(String ra,int page,int nbPage)throws Exception{
        ArrayList<Object>list=ray.pagination(ra,page,nbPage);
        ArrayList<Type>liste=new ArrayList<Type>();
        for (int i = 0; i <list.size(); i++) {
            liste.add((Type)list.get(i));
        }
        return liste;
    }
    public String get(int id)throws Exception{
        Type t=new Type(null);
        t.setIdtype(id);
        return ((Type)ray.find(t).get(0)).getIntitule();
    }
}
