/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import annotation.Attribut;
import annotation.NomTable;
import java.sql.Date;
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
@Table(name="film")
@NomTable(nom="film")
public class Film {
     @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="idfilm")
    @SequenceGenerator(name="idfilm", sequenceName="idfilm", allocationSize=1)
    @Attribut(columnName = "idfilm",isprimarykey = true)
     int idfilm;
     @Attribut(columnName = "nomfilm")
     String nomfilm;
     @Attribut(columnName = "datecreation")
     Timestamp datecreation;
     @Attribut(columnName = "image")
     String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Timestamp getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Timestamp datecreation) {
        this.datecreation = datecreation;
    }
     
   
     
    public Film() {
        this.idfilm = -1;
        
    }
    public Film(int idfilm, String nomfilm) {
        this.idfilm = -1;
        this.nomfilm = nomfilm;
    }

    public int getIdfilm() {
        return idfilm;
    }

    public void setIdfilm(int idfilm) {
        this.idfilm = idfilm;
    }

    public String getNomfilm() {
        return nomfilm;
    }

    public void setNomfilm(String nomfilm) {
        this.nomfilm = nomfilm;
    }

    public Film(int idfilm, String nomfilm, Timestamp datecreation) {
        this.idfilm = idfilm;
        this.nomfilm = nomfilm;
        this.datecreation = datecreation;
    }
    
}
