/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import HibernateDao.GenericDao;
import annotation.Attribut;
import annotation.NomTable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author USER
 */
@Entity
@Table(name="Objectinsert")
@NomTable(nom="Objectinsert")
public class Objectinsert {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="idobjectinsert")
    @SequenceGenerator(name="idobjectinsert", sequenceName="idobjectinsert", allocationSize=1)
    @Attribut(columnName = "idobjectinsert",isprimarykey = true)
    int idobjectinsert;
    @Attribut(columnName = "titre")
    String titre;
    @Attribut(columnName = "description")
    String description;
    @Attribut(columnName = "photo")
    String photo;
    @Attribut(columnName = "un")
    Date un;
    @Attribut(columnName = "deux")
    Date deux;
    @Attribut(columnName = "idtype")
    int idtype;
    @Attribut(columnName = "etat")
    int etat;
    @Attribut(columnName = "idauteur")
    int idauteur;
    @Attribut(columnName = "datecreation")
    Timestamp datecreation;
    @Attribut(columnName = "datepub")
    Timestamp datepub;
    
    
    public int getIdobjectinsert() {
        return idobjectinsert;
    }

    public void setIdobjectinsert(int idobjectinsert) {
        this.idobjectinsert = idobjectinsert;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String descprition) {
        this.description = descprition;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getUn() {
        return un;
    }

    public void setUn(Date un) {
        this.un = un;
    }

    public Date getDeux() {
        return deux;
    }

    public void setDeux(Date deux) {
        this.deux = deux;
    }

    public int getIdtype() {
        return idtype;
    }

    public void setIdtype(int idtype) {
        this.idtype = idtype;
    }

    public Objectinsert(String titre, String description, String photo, Date un, Date deux, int idtype) {
        this.idobjectinsert = -1;
        this.titre = titre;
        this.description = description;
        this.photo = photo;
        this.un = un;
        this.deux = deux;
        this.idtype = idtype;
        this.idauteur=-1;
        this.etat=-1;
    }
    
    public Objectinsert(int idobjectinsert, String titre, String description, String photo, Date un, Date deux, int idtype, int etat, int idauteur) {
        this.idobjectinsert = -1;
        this.titre = titre;
        this.description = description;
        this.photo = photo;
        this.un = un;
        this.deux = deux;
        this.idtype = -1;
        this.etat = -1;
        this.idauteur = -1;
    }

    public Objectinsert(int idobjectinsert) {
        this.idobjectinsert = idobjectinsert;
        this.titre = null;
        this.description = null;
        this.photo = null;
        this.un = null;
        this.deux = null;
        this.idtype =-1;
        this.etat =-1;
        this.idauteur = -1;
        this.datecreation = null;
        this.datepub = null;
    }
    
    public Objectinsert() {
        this.idauteur=-1;
        this.etat=-1;
        this.idtype=-1;
        this.idobjectinsert=-1;
    }
    public String getId(){
        return "idobjectinsert";
    }
    public String type()throws Exception{
        Type ray=new Type(null);
        ray.setIdtype(this.getIdtype());
        return ((Type)new GenericDao().find(ray).get(0)).getIntitule();
    }

    public int getEtat() {
        return etat;
    }
    public Acteur getAuteur() throws Exception{
        Acteur ray=new Acteur();
        ray.setIdauteur(this.getIdauteur());
        return ((Acteur)new GenericDao().find(ray).get(0));
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getIdauteur() {
        return idauteur;
    }

    public void setIdauteur(int idauteur) {
        this.idauteur = idauteur;
    }

    public Date getDatecreation() {
        return datecreation;
    }

 
    public Date getDatepub() {
        return datepub;
    }

    public void setDateCreation(Timestamp time) {
       this.datecreation=time;
    }
     public void setDatepub(Timestamp datepub) {
        this.datepub= datepub;
    }

   
    
}
