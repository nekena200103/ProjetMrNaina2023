/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entity.Action;
import entity.Replique;
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
public class RepliqueService {
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
    public void save(Replique obj)throws Exception{
        ray.save(obj);
    }
    public void update(Replique obj)throws Exception{
        ray.update(obj);
    }
    public void delete(Replique obj)throws Exception{
        ray.delete(obj);
    }
    public ArrayList<Replique> find(Replique obj)throws Exception{
        System.out.println("Ray "+ray );
        ArrayList<Object>list=ray.find(obj);
        ArrayList<Replique>liste=new ArrayList<>();
        for (int i = 0; i <list.size(); i++) {
            liste.add((Replique)list.get(i));
        }
        return liste;
    }
    
    public ArrayList<Replique> pagination(Replique ra,int page,int nbPage,int etat)throws Exception{
        String str="";
       if(etat==1){
           str="from Replique WHERE idaction="+ra.getIdaction();
       }else{
           try{
               str="from Replique WHERE LOWER(replique) like '%"+ra.getReplique()+"%'";
           }catch(Exception e){
           }
       }
        ArrayList<Object>list=ray.pagination(str,page,nbPage);
        ArrayList<Replique>liste=new ArrayList<Replique>();
        for (int i = 0; i <list.size(); i++) {
            liste.add((Replique)list.get(i));
        }
        return liste;
    }
    public ArrayList<Replique> paginationFront(Replique ra,int page,int nbPage,int etat)throws Exception{
        String str="";
       if(etat==1){
           str="from Replique WHERE idaction="+ra.getReplique();
       }else{
           try{
               str="from Replique WHERE LOWER(replique) like '%"+ra.getReplique()+"%'";
           }catch(Exception e){
           }
       }
        ArrayList<Object>list=ray.pagination(str,page,nbPage);
   
        ArrayList<Replique>liste=new ArrayList<Replique>();
        for (int i = 0; i <list.size(); i++) {
            liste.add((Replique)list.get(i));
        }
        return liste;
    }
    
    public int nombreDePage(ArrayList<Replique>ray,int nbPage)throws Exception{
        int mod=(ray.size()%nbPage);
        int valiny=(int)(ray.size()/nbPage);
        if(mod==0){
            return valiny;
        }
        return valiny+1;       
    }
}
