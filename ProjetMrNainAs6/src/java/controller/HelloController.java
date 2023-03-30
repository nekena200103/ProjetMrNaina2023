/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import Service.TypeService;
import Service.*;
import entity.Acteur;
import entity.Action;
import entity.Film;
import entity.Indisponibilite;
import entity.Logback;
import entity.Objectinsert;
import entity.Plateau;
import entity.Replique;
import entity.Scene;
import entity.Type;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.datetime.joda.LocalDateTimeParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
/**
 *
 * @author USER
 */
@Controller
public class HelloController {
    @Autowired
    ObjectinsertService oi; 
    @Autowired
    TypeService tp;
    @Autowired
    LogbackService lg;
    @Autowired
    FilmService fil;
    @Autowired
    ActeurService as;
    @Autowired
    SceneService ss;
    @Autowired
    ActionService actionserv;
    @Autowired
    RepliqueService repliqueserv;
     @Autowired
    PersonneService pserv;
     @Autowired
    PlateauService platserv;
      @Autowired
    IndisponibiliteService indserv;
     @RequestMapping(value="/")
    public String  debut (Model mod) throws Exception{
        Objectinsert obins=new Objectinsert();
        mod.addAttribute("pubs",oi.paginationFront(obins, 1, 1, 1));    
       return "indexo";
    }
    @RequestMapping(value="/planning",method=RequestMethod.POST)
    public String  planning (Model mod,@RequestParam("checkbox") ArrayList<String> allParams) throws Exception{
        ArrayList[] tabarrayact=new ArrayList[allParams.size()];
        ArrayList<Plateau> plat=platserv.find(new Plateau(null, -1, -1));
        ArrayList<Indisponibilite> arrindispo=indserv.find(new Indisponibilite(-1, -1, null,null,null));
        for (int i = 0; i < allParams.size(); i++) {
            String get = allParams.get(i);
            ArrayList<Action> actionrehetra=actionserv.find(new Action(-1,null,Integer.parseInt(get),-1));
            tabarrayact[i]=actionrehetra;
            int totalscene=0;
            for (int j = 0; j < actionrehetra.size(); j++) {
                Action get1 = actionrehetra.get(j);
                totalscene=totalscene+get1.getDuree();
                
            }
            int indiceplateau=0;
            int totaldejour=totalscene/8;
            Date dateajd=Date.valueOf(LocalDate.now());
            for (int j = 0; j < totaldejour; j++) {
               LocalDate local=LocalDate.now();
               LocalDate demain=local.plusDays(j);
               dateajd=Date.valueOf(demain);
               Plateau plateau=(Plateau)plat.get(indiceplateau);
                ArrayList<Indisponibilite> arrindispo2=new ArrayList<>();
                for (int k = 0; k < arrindispo.size(); k++) {
                    Indisponibilite get1 = arrindispo.get(k);
                    if (get1.getIdplateau()==plateau.getIdplateau()) {
                        arrindispo2.add(get1);
                    }
                }
               boolean dateindisponible=false;
               int nisyindisponible=0;
                for (int k = 0; k < arrindispo2.size(); k++) {
                    Indisponibilite get1 = (Indisponibilite)arrindispo2.get(k);
                    if ((get1.getHeuredebut().before(dateajd)&&get1.getHeurefin().after(dateajd))) {
                        indiceplateau++;
                        nisyindisponible++;
                        break;
                    }
                }
                if (nisyindisponible==0) {
                    Indisponibilite indice=new Indisponibilite();
                    indice.setIdplateau(plateau.getIdplateau());
                    indice.setHeuredebut(Timestamp.valueOf(dateajd.toString()+" 00:00:00"));
                    indice.setHeurefin(Timestamp.valueOf(dateajd.toString()+" 00:00:00"));
                    indice.setRaison(get);
                    
                    
                    indserv.save(indice);
                }
            }
            ArrayList<Indisponibilite> arrindispo2=indserv.find(new Indisponibilite(-1, -1, null,null,null));
            mod.addAttribute("indispo", arrindispo2);
            
        }
        
        System.out.println("size"+arrindispo.size());
        /*for (int i = 0; i < arrindispo.size(); i++) {
            Indisponibilite get = arrindispo.get(i);
            System.out.println(get.getHeuredebut()+"-"+get.getHeurefin());
        }*/
        
        
       return "planning";
    }
    /*@RequestMapping(value="/planning",method=RequestMethod.POST)
    public String handleCheckBoxesSubmit(@ModelAttribute CheckBoxModel checkBoxModel, Model model) {
    List<CheckBoxItem> selectedItems = checkBoxModel.getIte­ms().stream().filter(CheckBoxItem::isChecked).collect(Collectors.toList());
    // Faites quelque chose avec les éléments sélectionnés
    return "redirect:/checkboxes";
    }*/
//    @RequestMapping(value="/login")
//    public String  login (Model mod,@RequestParam Map<String,String> allParams){
//        return "login";
//       
//    }
   
    @RequestMapping(value="/validationAdmin")
    public String  validation (Model mod,@RequestParam Map<String,String> allParams) throws Exception{
        Logback ray=lg.user(allParams.get("identifiant"),allParams.get("motdepasse"));
       if(ray!=null){
           Film obins=new Film();
           mod.addAttribute("pubs",fil.find(obins));  
           return "redirect:listfilm";
       }else{
           return "error";
       }
       
    }
    @RequestMapping(value="/validationAuteur",method=RequestMethod.GET)
    public String  validationAuteur (Model mod,@RequestParam Map<String,String> allParams){
        Acteur ray=as.user(allParams.get("identifiant"),allParams.get("motdepasse"));
       if(ray!=null){
            Objectinsert ray2=new Objectinsert();
        try{
            mod.addAttribute("class", ray2.getClass().getName());
            mod.addAttribute("listeType",tp.find(new Type(null)));
            mod.addAttribute("idutil",ray.getIdauteur());
        }catch(Exception e){
            
        }
       return "insertion";
           
       }else{
           return "error";
       }
    }
    @RequestMapping(value="/valider",method=RequestMethod.GET)
    public String  validationPost (Model mod,@RequestParam Map<String,String> allParams) throws Exception{
        Objectinsert obj=new Objectinsert(Integer.parseInt(allParams.get("idpost")));
        
        ArrayList<Objectinsert> julius=oi.find(obj);
        
        julius.get(0).setEtat(1);
        
        oi.update( julius.get(0));
        return "redirect:listfilm";
     
    }
    @RequestMapping(value="/publier",method=RequestMethod.GET)
    public String  publicationPost (Model mod,@RequestParam Map<String,String> allParams) throws Exception{
        Objectinsert obj=new Objectinsert(Integer.parseInt(allParams.get("idpost")));
        
        String pub=allParams.get("publication").replace('T',' ');
        ArrayList<Objectinsert> julius=oi.find(obj);
         julius.get(0).setDatepub(Timestamp.valueOf(pub+":00"));
        oi.update( julius.get(0));
        return "redirect:list";
     
    }
    @RequestMapping(value="/acc")
    public String  acc (Model mod){
        Objectinsert rayo=new Objectinsert();
        try{
            mod.addAttribute("pubs",lg.find(rayo));
        }catch(Exception e){
            
        }
       return "acceuil";
    }
    @RequestMapping(value="/debut/",method=RequestMethod.GET)
    public String  entree (Model mod){
        Objectinsert ray=new Objectinsert();
        try{
            mod.addAttribute("class", ray.getClass().getName());
            mod.addAttribute("listeType",tp.find(new Type(null)));
        }catch(Exception e){
            
        }
       return "insertion";
    }
    @RequestMapping(value="/insertion",method=RequestMethod.POST)
    public String  insertion (Model mod,@RequestParam CommonsMultipartFile file,@RequestParam Map<String,String> allParams){
        byte[] bytes = file.getBytes();
        
        //Objectinsert ray=new Objectinsert(allParams.get("titre"),allParams.get("description"), util.Util.FtoBase64(bytes), util.Util.toGod(allParams.get("un")),util.Util.toGod(allParams.get("deux")), Integer.parseInt(allParams.get("idtype")));
        try{
            Object obj=util.Util.traductionParameterDynamique(allParams);
            Objectinsert ray=(Objectinsert)obj;
            ray.setPhoto(util.Util.FtoBase64(bytes));
            Timestamp time=Timestamp.valueOf(LocalDateTime.now()) ;
            ray.setDateCreation(time);
            oi.save(ray);
            Objectinsert obins=new Objectinsert();
            mod.addAttribute("pubs",oi.paginationFront(obins, 1, 1, 1)); 
        }catch(Exception e){
            mod.addAttribute("message",e.getMessage());
            e.printStackTrace();
        }
       return "redirect:list2";
    }
    @RequestMapping(value="/insertionfilm",method=RequestMethod.POST)
    public String  insertionfilm (Model mod,@RequestParam CommonsMultipartFile file,@RequestParam Map<String,String> allParams){
        byte[] bytes = file.getBytes();
        
        //Objectinsert ray=new Objectinsert(allParams.get("titre"),allParams.get("description"), util.Util.FtoBase64(bytes), util.Util.toGod(allParams.get("un")),util.Util.toGod(allParams.get("deux")), Integer.parseInt(allParams.get("idtype")));
        try{
            
            //Object obj=util.Util.traductionParameterDynamique(allParams);
            Film ray=(Film)new Film();
            ray.setNomfilm(allParams.get("nomfilm"));
            ray.setImage(util.Util.FtoBase64(bytes));
            Timestamp time=Timestamp.valueOf(LocalDateTime.now()) ;
            ray.setDatecreation(time);
            fil.save(ray);
            Film obins=new Film();
            mod.addAttribute("pubs",fil.find(obins));  
            
        }catch(Exception e){
            mod.addAttribute("message",e.getMessage());
            e.printStackTrace();
        }
       return "redirect:listfilm";
    }
    @RequestMapping(value="/insertionscene",method=RequestMethod.POST)
    public String  insertionscene (Model mod,@RequestParam Map<String,String> allParams){
        
        
        //Objectinsert ray=new Objectinsert(allParams.get("titre"),allParams.get("description"), util.Util.FtoBase64(bytes), util.Util.toGod(allParams.get("un")),util.Util.toGod(allParams.get("deux")), Integer.parseInt(allParams.get("idtype")));
        try{
            
            //Object obj=util.Util.traductionParameterDynamique(allParams);
            Scene ray=(Scene)new Scene();
            ray.setDescription(allParams.get("description"));
            ray.setIdfilm(Integer.parseInt(allParams.get("idfilm")));
            
            ss.save(ray);
            Scene obins=new Scene();
            mod.addAttribute("pubs",ss.find(obins));  
            
        }catch(Exception e){
            mod.addAttribute("message",e.getMessage());
            e.printStackTrace();
        }
       return "redirect:listscene?film="+allParams.get("idfilm");
    }
    @RequestMapping(value="/insertionaction",method=RequestMethod.POST)
    public String  insertionaction (Model mod,@RequestParam Map<String,String> allParams){
        
        
        //Objectinsert ray=new Objectinsert(allParams.get("titre"),allParams.get("description"), util.Util.FtoBase64(bytes), util.Util.toGod(allParams.get("un")),util.Util.toGod(allParams.get("deux")), Integer.parseInt(allParams.get("idtype")));
        try{
            
            //Object obj=util.Util.traductionParameterDynamique(allParams);
            Action ray=(Action)new Action();
            ray.setDescription_action(allParams.get("description_action"));
            ray.setDuree(Integer.parseInt(allParams.get("duree")));
            ray.setIdscene(Integer.parseInt(allParams.get("idscene")));
            
            actionserv.save(ray);
            Action obins=new Action();
            mod.addAttribute("pubs",actionserv.find(obins));  
            
        }catch(Exception e){
            mod.addAttribute("message",e.getMessage());
            e.printStackTrace();
        }
       return "redirect:listaction?scene="+allParams.get("idscene");
    }
    @RequestMapping(value="/insertionreplique",method=RequestMethod.POST)
    public String  insertionreplique (Model mod,@RequestParam Map<String,String> allParams){
        
        
        //Objectinsert ray=new Objectinsert(allParams.get("titre"),allParams.get("description"), util.Util.FtoBase64(bytes), util.Util.toGod(allParams.get("un")),util.Util.toGod(allParams.get("deux")), Integer.parseInt(allParams.get("idtype")));
        try{
            
            //Object obj=util.Util.traductionParameterDynamique(allParams);
            Replique ray=(Replique)new Replique();
            ray.setReplique(allParams.get("replique"));
            ray.setIdpersonne(Integer.parseInt(allParams.get("idpersonne")));
            ray.setIdaction(Integer.parseInt(allParams.get("idaction")));
            
            repliqueserv.save(ray);
            Replique obins=new Replique();
            mod.addAttribute("pubs",repliqueserv.find(obins));  
            
        }catch(Exception e){
            mod.addAttribute("message",e.getMessage());
            e.printStackTrace();
        }
       return "redirect:listreplique?action="+allParams.get("idaction");
    }
    @RequestMapping(value="/insert",method=RequestMethod.GET)
    public String  insert (Model mod,@RequestParam Map<String,String> allParams){
       return "index";
    }
    @RequestMapping(value="/listfilm",method=RequestMethod.GET)
    public String  listfilm (Model mod,@RequestParam Map<String,String> allParams){
        int nbPage=3;
        String sessionrecherche="tsisy";
       //int recherche=0;
       int numPage=1;
       if(allParams.get("numPage")!=""&&allParams.get("numPage")!=null){
           numPage=Integer.parseInt(allParams.get("numPage"));
       }
       if((allParams.get("recherche")!=""&&allParams.get("recherche")!=null)){
           try{
               numPage=1;
               sessionrecherche=allParams.get("recherche");
               mod.addAttribute("listeObj",fil.pagination(new Film(-1,allParams.get("recherche"),null), numPage, nbPage,0));
               mod.addAttribute("nombrePage",fil.nombreDePage(fil.pagination(new Film(-1,allParams.get("recherche"),null), numPage, 0,0), nbPage));
               if(fil.pagination(new Film(-1,allParams.get("recherche"),null), numPage+1, nbPage,0).size()>0){
                   mod.addAttribute("ariana",1);
               }else{
                   mod.addAttribute("ariana",0);
               }
           }catch(Exception e){ 
               e.printStackTrace();
           }
     
       }else if((allParams.get("sessionrecherche")!=""&&allParams.get("sessionrecherche")!=null&&allParams.get("sessionrecherche").compareToIgnoreCase("tsisy")!=0)){
           try{
               sessionrecherche=allParams.get("sessionrecherche");
               mod.addAttribute("listeObj",fil.pagination(new Film(-1,allParams.get("sessionrecherche"),null), numPage, nbPage,0));
               mod.addAttribute("nombrePage",fil.nombreDePage(fil.pagination(new Film(-1,allParams.get("sessionrecherche"),null), numPage, 0,0), nbPage));
               if(fil.pagination(new Film(-1,allParams.get("sessionrecherche"),null), numPage+1, nbPage,0).size()>0){
                   mod.addAttribute("ariana",1);
               }else{
                   mod.addAttribute("ariana",0);
               }
           }catch(Exception e){ 
               
           }
    }else {
           try{
               mod.addAttribute("listeObj",fil.pagination(new Film(-1,null,null), numPage, nbPage,1));
               mod.addAttribute("nombrePage",fil.nombreDePage(fil.pagination(new Film(-1,null,null), numPage, 0,1), nbPage));
               if(fil.pagination(new Film(-1,null,null), numPage+1, nbPage,1).size()>0){
                   mod.addAttribute("ariana",1);
               }else{
                   mod.addAttribute("ariana",0);
               }
           }catch(Exception e){
               e.printStackTrace();
           }
        }
      
       mod.addAttribute("sessionrecherche",sessionrecherche);
       mod.addAttribute("nbPage",nbPage);
       mod.addAttribute("numPage",numPage);
       
       return "listefilm";
    }
    @RequestMapping(value="/listscene",method=RequestMethod.GET)
    public String  listscene (Model mod,@RequestParam Map<String,String> allParams) throws Exception{
        int idfilm=-1;
        if ((allParams.get("film")!=""&&allParams.get("film")!=null)) {
                   idfilm=Integer.parseInt(allParams.get("film"));
                   Film film=new Film(idfilm,null,null);
                   mod.addAttribute("film",fil.find(film).get(0));
        }
        Film film=new Film(idfilm,null,null);
        int nbPage=3;
        String sessionrecherche="tsisy";
       //int recherche=0;
       int numPage=1;
       if(allParams.get("numPage")!=""&&allParams.get("numPage")!=null){
           numPage=Integer.parseInt(allParams.get("numPage"));
       }
       if((allParams.get("recherche")!=""&&allParams.get("recherche")!=null)){
           try{
               numPage=1;
               sessionrecherche=allParams.get("recherche");
               mod.addAttribute("listeObj",ss.pagination(new Scene(-1,-1,-1,-1,idfilm,allParams.get("recherche")), numPage, nbPage,0));
               mod.addAttribute("nombrePage",ss.nombreDePage(ss.pagination(new Scene(-1,-1,-1,-1,-1,allParams.get("recherche")), numPage, 0,0), nbPage));
               if(ss.pagination(new Scene(-1,-1,-1,-1,idfilm,allParams.get("recherche")), numPage+1, nbPage,0).size()>0){
                   mod.addAttribute("ariana",1);
               }else{
                   mod.addAttribute("ariana",0);
               }
           }catch(Exception e){ 
               e.printStackTrace();
           }
     
       }else if((allParams.get("sessionrecherche")!=""&&allParams.get("sessionrecherche")!=null&&allParams.get("sessionrecherche").compareToIgnoreCase("tsisy")!=0)){
           try{
               sessionrecherche=allParams.get("sessionrecherche");
               mod.addAttribute("listeObj",ss.pagination(new Scene(-1,-1,-1,-1,idfilm,allParams.get("recherche")), numPage, nbPage,0));
               mod.addAttribute("nombrePage",ss.nombreDePage(ss.pagination(new Scene(-1,-1,-1,-1,-1,allParams.get("recherche")), numPage, 0,0), nbPage));
               if(ss.pagination(new Scene(-1,-1,-1,-1,idfilm,allParams.get("recherche")), numPage+1, nbPage,0).size()>0){
                   mod.addAttribute("ariana",1);
               }else{
                   mod.addAttribute("ariana",0);
               }
           }catch(Exception e){ 
               
           }
    }else {
           try{
               

               mod.addAttribute("listeObj",ss.pagination(new Scene(-1,-1,-1,-1,idfilm,null), numPage, nbPage,1));
               mod.addAttribute("nombrePage",ss.nombreDePage(ss.pagination(new Scene(-1,-1,-1,-1,idfilm,null), numPage, 0,1), nbPage));
               if(ss.pagination(new Scene(-1,-1,-1,-1,idfilm,null), numPage+1, nbPage,1).size()>0){
                   mod.addAttribute("ariana",1);
               }else{
                   mod.addAttribute("ariana",0);
               }
           }catch(Exception e){
               e.printStackTrace();
           }
        }
      
       mod.addAttribute("sessionrecherche",sessionrecherche);
       mod.addAttribute("nbPage",nbPage);
       mod.addAttribute("numPage",numPage);
       
       return "creerscene";
    }
    @RequestMapping(value="/listaction",method=RequestMethod.GET)
    public String  listaction (Model mod,@RequestParam Map<String,String> allParams) throws Exception{
        int idscene=-1;
        if ((allParams.get("scene")!=""&&allParams.get("scene")!=null)) {
                   idscene=Integer.parseInt(allParams.get("scene"));
                   Scene scene=new Scene(idscene,-1, -1, -1, -1, null);
                   mod.addAttribute("scene",ss.find(scene).get(0));
        }
        
        int nbPage=3;
        String sessionrecherche="tsisy";
       //int recherche=0;
       int numPage=1;
       if(allParams.get("numPage")!=""&&allParams.get("numPage")!=null){
           numPage=Integer.parseInt(allParams.get("numPage"));
       }
       if((allParams.get("recherche")!=""&&allParams.get("recherche")!=null)){
           try{
               numPage=1;
               sessionrecherche=allParams.get("recherche");
               mod.addAttribute("listeObj",actionserv.pagination(new Action(-1,allParams.get("recherche"),idscene,-1), numPage, nbPage,0));
               mod.addAttribute("nombrePage",actionserv.nombreDePage(actionserv.pagination(new Action(-1,allParams.get("recherche"),idscene,-1), numPage, 0,0), nbPage));
               if(actionserv.pagination(new Action(-1,allParams.get("recherche"),idscene,-1), numPage+1, nbPage,0).size()>0){
                   mod.addAttribute("ariana",1);
               }else{
                   mod.addAttribute("ariana",0);
               }
           }catch(Exception e){ 
               e.printStackTrace();
           }
     
       }else if((allParams.get("sessionrecherche")!=""&&allParams.get("sessionrecherche")!=null&&allParams.get("sessionrecherche").compareToIgnoreCase("tsisy")!=0)){
           try{
               sessionrecherche=allParams.get("sessionrecherche");
               mod.addAttribute("listeObj",actionserv.pagination(new Action(-1,allParams.get("recherche"),idscene,-1), numPage, nbPage,0));
               mod.addAttribute("nombrePage",actionserv.nombreDePage(actionserv.pagination(new Action(-1,allParams.get("recherche"),idscene,-1), numPage, 0,0), nbPage));
               if(actionserv.pagination(new Action(-1,allParams.get("recherche"),idscene,-1), numPage+1, nbPage,0).size()>0){
                   mod.addAttribute("ariana",1);
               }else{
                   mod.addAttribute("ariana",0);
               }
           }catch(Exception e){ 
               
           }
    }else {
           try{
               

              mod.addAttribute("listeObj",actionserv.pagination(new Action(-1,null,idscene,-1), numPage, nbPage,1));
               mod.addAttribute("nombrePage",actionserv.nombreDePage(actionserv.pagination(new Action(-1,null,idscene,-1), numPage, 0,1), nbPage));
               if(actionserv.pagination(new Action(-1,null,idscene,-1), numPage+1, nbPage,1).size()>0){
                   mod.addAttribute("ariana",1);
               }else{
                   mod.addAttribute("ariana",0);
               }
           }catch(Exception e){
               e.printStackTrace();
           }
        }
      
       mod.addAttribute("sessionrecherche",sessionrecherche);
       mod.addAttribute("nbPage",nbPage);
       mod.addAttribute("numPage",numPage);
       
       return "creeraction";
    }
     @RequestMapping(value="/listreplique",method=RequestMethod.GET)
    public String  listreplique (Model mod,@RequestParam Map<String,String> allParams) throws Exception{
        int idscene=-1;
        if ((allParams.get("action")!=""&&allParams.get("action")!=null)) {
                   idscene=Integer.parseInt(allParams.get("action"));
                   Action scene=new Action(idscene, null, -1, -1);
                   mod.addAttribute("action",actionserv.find(scene).get(0));
        }
        mod.addAttribute("personne",pserv.findAll());
        int nbPage=3;
        String sessionrecherche="tsisy";
       //int recherche=0;
       int numPage=1;
       if(allParams.get("numPage")!=""&&allParams.get("numPage")!=null){
           numPage=Integer.parseInt(allParams.get("numPage"));
       }
       if((allParams.get("recherche")!=""&&allParams.get("recherche")!=null)){
           try{
               numPage=1;
               sessionrecherche=allParams.get("recherche");
               mod.addAttribute("listeObj",repliqueserv.pagination(new Replique(-1,-1,-1, sessionrecherche, idscene), numPage, nbPage,0));
               mod.addAttribute("nombrePage",repliqueserv.nombreDePage(repliqueserv.pagination(new Replique(-1,-1,-1, sessionrecherche, idscene), numPage, 0,0), nbPage));
               if(repliqueserv.pagination(new Replique(-1,-1,-1, sessionrecherche, idscene), numPage+1, nbPage,0).size()>0){
                   mod.addAttribute("ariana",1);
               }else{
                   mod.addAttribute("ariana",0);
               }
           }catch(Exception e){ 
               e.printStackTrace();
           }
     
       }else if((allParams.get("sessionrecherche")!=""&&allParams.get("sessionrecherche")!=null&&allParams.get("sessionrecherche").compareToIgnoreCase("tsisy")!=0)){
           try{
               sessionrecherche=allParams.get("sessionrecherche");
               mod.addAttribute("listeObj",repliqueserv.pagination(new Replique(-1,-1,-1, sessionrecherche, idscene), numPage, nbPage,0));
               mod.addAttribute("nombrePage",repliqueserv.nombreDePage(repliqueserv.pagination(new Replique(-1,-1,-1, sessionrecherche, idscene), numPage, 0,0), nbPage));
               if(repliqueserv.pagination(new Replique(-1,-1,-1, sessionrecherche, idscene), numPage+1, nbPage,0).size()>0){
                   mod.addAttribute("ariana",1);
               }else{
                   mod.addAttribute("ariana",0);
               }
           }catch(Exception e){ 
               
           }
    }else {
           try{
               

              mod.addAttribute("listeObj",repliqueserv.pagination(new Replique(-1,-1,-1, null, idscene), numPage, nbPage,1));
               mod.addAttribute("nombrePage",repliqueserv.nombreDePage(repliqueserv.pagination(new Replique(-1,-1,-1,null, idscene), numPage, 0,1), nbPage));
               if(repliqueserv.pagination(new Replique(-1,-1,-1,null, idscene), numPage+1, nbPage,1).size()>0){
                   mod.addAttribute("ariana",1);
               }else{
                   mod.addAttribute("ariana",0);
               }
           }catch(Exception e){
               e.printStackTrace();
           }
        }
      
       mod.addAttribute("sessionrecherche",sessionrecherche);
       mod.addAttribute("nbPage",nbPage);
       mod.addAttribute("numPage",numPage);
       
       return "creerreplique";
    }
    
}

