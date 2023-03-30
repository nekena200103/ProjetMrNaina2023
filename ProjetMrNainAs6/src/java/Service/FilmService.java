/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entity.Film;
import entity.Objectinsert;
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
public class FilmService {
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
    public void save(Film obj)throws Exception{
        ray.save(obj);
    }
    public void update(Film obj)throws Exception{
        ray.update(obj);
    }
    public void delete(Film obj)throws Exception{
        ray.delete(obj);
    }
    public ArrayList<Film> find(Film obj)throws Exception{
        System.out.println("Ray "+ray );
        ArrayList<Object>list=ray.find(obj);
        ArrayList<Film>liste=new ArrayList<>();
        for (int i = 0; i <list.size(); i++) {
            liste.add((Film)list.get(i));
        }
        return liste;
    }
    
    public ArrayList<Film> pagination(Film ra,int page,int nbPage,int etat)throws Exception{
        String str="";
       if(etat==1){
           str="from Film ";
       }else{
           try{
               str="from Film WHERE LOWER(nomfilm) like '%"+ra.getNomfilm().toLowerCase()+"%'";
           }catch(Exception e){
           }
       }
        ArrayList<Object>list=ray.pagination(str,page,nbPage);
        System.out.println("taygod"+list.size());
        ArrayList<Film>liste=new ArrayList<Film>();
        for (int i = 0; i <list.size(); i++) {
            liste.add((Film)list.get(i));
        }
        return liste;
    }
    public ArrayList<Film> paginationFront(Film ra,int page,int nbPage,int etat)throws Exception{
        String str="";
       if(etat==1){
           str="from film Where etat=1 and datecreation>CURRENT_TIMESTAMP ";
       }else{
           try{
               str="from Objectinsert WHERE LOWER(nomfilm) like '%"+ra.getNomfilm().toLowerCase()+"%' datecreation>CURRENT_TIMESTAMP  ";
           }catch(Exception e){
           }
       }
        ArrayList<Object>list=ray.pagination(str,page,nbPage);
        System.out.println("taygod"+list.size());
        ArrayList<Film>liste=new ArrayList<Film>();
        for (int i = 0; i <list.size(); i++) {
            liste.add((Film)list.get(i));
        }
        return liste;
    }
    
    public int nombreDePage(ArrayList<Film>ray,int nbPage)throws Exception{
        int mod=(ray.size()%nbPage);
        int valiny=(int)(ray.size()/nbPage);
        if(mod==0){
            return valiny;
        }
        return valiny+1;       
    }
    
}
