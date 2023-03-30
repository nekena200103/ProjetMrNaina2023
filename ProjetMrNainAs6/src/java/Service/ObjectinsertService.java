/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import HibernateDao.HibernateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import inter.InterfaceDao;
import java.util.ArrayList;
import entity.*;

/**
 *
 * @author USER
 */
@Component
public class ObjectinsertService {
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
    public void save(Objectinsert obj)throws Exception{
        ray.save(obj);
    }
    public void update(Objectinsert obj)throws Exception{
        ray.update(obj);
    }
    public void delete(Objectinsert obj)throws Exception{
        ray.delete(obj);
    }
    
    public ArrayList<Objectinsert> find(Objectinsert obj)throws Exception{
        System.out.println("Ray "+ray );
        ArrayList<Object>list=ray.find(obj);
        ArrayList<Objectinsert>liste=new ArrayList<>();
        for (int i = 0; i <list.size(); i++) {
            liste.add((Objectinsert)list.get(i));
        }
        return liste;
    }
    
    public ArrayList<Objectinsert> pagination(Objectinsert ra,int page,int nbPage,int etat)throws Exception{
        String str="";
       if(etat==1){
           str="from Objectinsert ";
       }else{
           try{
               str="from Objectinsert WHERE LOWER(titre) like '%"+ra.getTitre().toLowerCase()+"%' or LOWER(description) like '%"+ra.getDescription().toLowerCase()+"%'";
           }catch(Exception e){
           }
       }
        ArrayList<Object>list=ray.pagination(str,page,nbPage);
        System.out.println("taygod"+list.size());
        ArrayList<Objectinsert>liste=new ArrayList<Objectinsert>();
        for (int i = 0; i <list.size(); i++) {
            liste.add((Objectinsert)list.get(i));
        }
        return liste;
    }
    public ArrayList<Objectinsert> paginationFront(Objectinsert ra,int page,int nbPage,int etat)throws Exception{
        String str="";
       if(etat==1){
           str="from Objectinsert Where etat=1 and datecreation>CURRENT_TIMESTAMP ";
       }else{
           try{
               str="from Objectinsert WHERE LOWER(titre) like '%"+ra.getTitre().toLowerCase()+"%' or LOWER(description) like '%"+ra.getDescription().toLowerCase()+"%'"
                       + "Where etat=1 and datecreation>CURRENT_TIMESTAMP  ";
           }catch(Exception e){
           }
       }
        ArrayList<Object>list=ray.pagination(str,page,nbPage);
        System.out.println("taygod"+list.size());
        ArrayList<Objectinsert>liste=new ArrayList<Objectinsert>();
        for (int i = 0; i <list.size(); i++) {
            liste.add((Objectinsert)list.get(i));
        }
        return liste;
    }
    
    public int nombreDePage(ArrayList<Objectinsert>ray,int nbPage)throws Exception{
        int mod=(ray.size()%nbPage);
        int valiny=(int)(ray.size()/nbPage);
        if(mod==0){
            return valiny;
        }
        return valiny+1;       
    }
    
}
