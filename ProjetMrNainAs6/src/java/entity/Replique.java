/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import annotation.Attribut;
import annotation.NomTable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Nekena
 */
@Entity
@Table(name="replique")
@NomTable(nom="replique")
public class Replique {
     @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="idreplique")
    @SequenceGenerator(name="idreplique", sequenceName="idreplique", allocationSize=1)
    @Attribut(columnName = "idreplique",isprimarykey = true)
    int idreplique;
     @Attribut(columnName = "idpersonne")
    int  idpersonne; 
     @Attribut(columnName = "ordre")
    int ordre ;
     @Attribut(columnName = "replique")
    String replique;
     @Attribut(columnName = "idaction")
    int idaction;

    public Replique() {
    }

    public Replique(int idreplique, int idpersonne, int ordre, String replique, int idaction) {
        this.idreplique = idreplique;
        this.idpersonne = idpersonne;
        this.ordre = ordre;
        this.replique = replique;
        this.idaction = idaction;
    }

     
    public int getIdreplique() {
        return idreplique;
    }

    public void setIdreplique(int idreplique) {
        this.idreplique = idreplique;
    }

    public int getIdpersonne() {
        return idpersonne;
    }

    public void setIdpersonne(int idpersonne) {
        this.idpersonne = idpersonne;
    }

    public int getOrdre() {
        return ordre;
    }

    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }

    public String getReplique() {
        return replique;
    }

    public void setReplique(String replique) {
        this.replique = replique;
    }

    public int getIdaction() {
        return idaction;
    }

    public void setIdaction(int idaction) {
        this.idaction = idaction;
    }
     
}
