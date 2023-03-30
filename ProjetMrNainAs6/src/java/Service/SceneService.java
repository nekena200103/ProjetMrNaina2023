/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entity.Film;
import entity.Scene;
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
public class SceneService {
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
    public void save(Scene obj)throws Exception{
        ray.save(obj);
    }
    public void update(Scene obj)throws Exception{
        ray.update(obj);
    }
    public void delete(Scene obj)throws Exception{
        ray.delete(obj);
    }
    public ArrayList<Scene> find(Scene obj)throws Exception{
        System.out.println("Ray "+ray );
        ArrayList<Object>list=ray.find(obj);
        ArrayList<Scene>liste=new ArrayList<>();
        for (int i = 0; i <list.size(); i++) {
            liste.add((Scene)list.get(i));
        }
        return liste;
    }
    
    public ArrayList<Scene> pagination(Scene ra,int page,int nbPage,int etat)throws Exception{
        String str="";
       if(etat==1){
           str="from Scene WHERE idfilm="+ra.getIdfilm();
       }else{
           try{
               str="from Scene WHERE LOWER(description) like '%"+ra.getDescription()+"%'";
           }catch(Exception e){
           }
       }
        ArrayList<Object>list=ray.pagination(str,page,nbPage);
        ArrayList<Scene>liste=new ArrayList<Scene>();
        for (int i = 0; i <list.size(); i++) {
            liste.add((Scene)list.get(i));
        }
        return liste;
    }
    public ArrayList<Scene> paginationFront(Scene ra,int page,int nbPage,int etat)throws Exception{
        String str="";
       if(etat==1){
           str="from Scene ";
       }else{
           try{
               str="from Scene WHERE LOWER(description) like '%"+ra.getDescription()+"%'   ";
           }catch(Exception e){
           }
       }
        ArrayList<Object>list=ray.pagination(str,page,nbPage);
        System.out.println("taygod"+list.size());
        ArrayList<Scene>liste=new ArrayList<Scene>();
        for (int i = 0; i <list.size(); i++) {
            liste.add((Scene)list.get(i));
        }
        return liste;
    }
    
    public int nombreDePage(ArrayList<Scene>ray,int nbPage)throws Exception{
        int mod=(ray.size()%nbPage);
        int valiny=(int)(ray.size()/nbPage);
        if(mod==0){
            return valiny;
        }
        return valiny+1;       
    }

}
