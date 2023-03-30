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
 * @author USER
 */
@Entity
@Table(name="Type")
@NomTable(nom="Type")
public class Type {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="idtype")
    @SequenceGenerator(name="idtype", sequenceName="idtype", allocationSize=1)
    @Attribut(columnName = "idtype",isprimarykey = true)
    int idtype;
    @Attribut(columnName = "intitule")
    String intitule;

    public int getIdtype() {
        return idtype;
    }

    public void setIdtype(int idtype) {
        this.idtype = idtype;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public Type(String intitule) {
        this.idtype = -1;
        this.intitule = intitule;
    }

    public Type() {
    }
    public String getId(){
        return "idtype";
    }
}
