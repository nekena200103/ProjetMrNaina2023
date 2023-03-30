/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;


import entity.Action;
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
public class ActionService {
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
    public void save(Action obj)throws Exception{
        ray.save(obj);
    }
    public void update(Action obj)throws Exception{
        ray.update(obj);
    }
    public void delete(Action obj)throws Exception{
        ray.delete(obj);
    }
    public ArrayList<Action> find(Action obj)throws Exception{
        System.out.println("Ray "+ray );
        ArrayList<Object>list=ray.find(obj);
        ArrayList<Action>liste=new ArrayList<>();
        for (int i = 0; i <list.size(); i++) {
            liste.add((Action)list.get(i));
        }
        return liste;
    }
    
    public ArrayList<Action> pagination(Action ra,int page,int nbPage,int etat)throws Exception{
        String str="";
       if(etat==1){
           str="from Action WHERE idscene="+ra.getIdscene();
       }else{
           try{
               str="from Action WHERE LOWER(description_action) like '%"+ra.getDescription_action()+"%'";
           }catch(Exception e){
           }
       }
        ArrayList<Object>list=ray.pagination(str,page,nbPage);
        ArrayList<Action>liste=new ArrayList<Action>();
        for (int i = 0; i <list.size(); i++) {
            liste.add((Action)list.get(i));
        }
        return liste;
    }
    public ArrayList<Action> paginationFront(Action ra,int page,int nbPage,int etat)throws Exception{
        String str="";
       if(etat==1){
           str="from Action WHERE idscene="+ra.getIdscene();
       }else{
           try{
               str="from Action WHERE LOWER(description_action) like '%"+ra.getDescription_action()+"%'";
           }catch(Exception e){
           }
       }
        ArrayList<Object>list=ray.pagination(str,page,nbPage);
   
        ArrayList<Action>liste=new ArrayList<Action>();
        for (int i = 0; i <list.size(); i++) {
            liste.add((Action)list.get(i));
        }
        return liste;
    }
    
    public int nombreDePage(ArrayList<Action>ray,int nbPage)throws Exception{
        int mod=(ray.size()%nbPage);
        int valiny=(int)(ray.size()/nbPage);
        if(mod==0){
            return valiny;
        }
        return valiny+1;       
    }

}
