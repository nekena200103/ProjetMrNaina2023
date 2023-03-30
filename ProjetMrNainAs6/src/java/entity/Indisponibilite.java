/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import annotation.Attribut;
import annotation.NomTable;
import java.sql.Timestamp;
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
@Table(name="indisponibilite")
@NomTable(nom="indisponibilite")
public class Indisponibilite {
   @Id
   @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="idindisponibilite")
   @SequenceGenerator(name="idindisponibilite", sequenceName="idindisponibilite", allocationSize=1)
   @Attribut(columnName = "idindisponibilite",isprimarykey = true)
   int idindisponibilite;
   @Attribut(columnName = "idplateau")
   int idplateau;
   @Attribut(columnName = "raison")
   String  raison;
    @Attribut(columnName = "heuredebut")
   Timestamp heuredebut;
     @Attribut(columnName = "heurefin")
   Timestamp heurefin;

    public Indisponibilite() {
    }

    public Indisponibilite(int idindisponibilite, int idplateau, String raison, Timestamp heuredebut, Timestamp heurefin) {
        this.idindisponibilite = idindisponibilite;
        this.idplateau = idplateau;
        this.raison = raison;
        this.heuredebut = heuredebut;
        this.heurefin = heurefin;
    }

    public int getIdindisponibilite() {
        return idindisponibilite;
    }

    public void setIdindisponibilite(int idindisponibilite) {
        this.idindisponibilite = idindisponibilite;
    }

    public int getIdplateau() {
        return idplateau;
    }

    public void setIdplateau(int idplateau) {
        this.idplateau = idplateau;
    }

    public String getRaison() {
        return raison;
    }

    public void setRaison(String raison) {
        this.raison = raison;
    }

    public Timestamp getHeuredebut() {
        return heuredebut;
    }

    public void setHeuredebut(Timestamp heuredebut) {
        this.heuredebut = heuredebut;
    }

    public Timestamp getHeurefin() {
        return heurefin;
    }

    public void setHeurefin(Timestamp heurefin) {
        this.heurefin = heurefin;
    }
     
}
