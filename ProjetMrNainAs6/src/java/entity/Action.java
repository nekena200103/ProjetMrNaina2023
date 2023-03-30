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
@Table(name="action")
@NomTable(nom="action")
public class Action {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="idaction")
    @SequenceGenerator(name="idaction", sequenceName="idaction", allocationSize=1)
    @Attribut(columnName = "idaction",isprimarykey = true)
    int idaction;
    @Attribut(columnName = "description_action")
    String description_action;
    @Attribut(columnName = "idscene")
    int idscene;
    @Attribut(columnName = "duree")
    int duree;

    public Action(int idaction, String description_action, int idscene, int duree) {
        this.idaction = idaction;
        this.description_action = description_action;
        this.idscene = idscene;
        this.duree = duree;
    }

    public Action() {
        idaction=-1;
    }

    public int getIdaction() {
        return idaction;
    }

    public void setIdaction(int idaction) {
        this.idaction = idaction;
    }

    public String getDescription_action() {
        return description_action;
    }

    public void setDescription_action(String description_action) {
        this.description_action = description_action;
    }

    public int getIdscene() {
        return idscene;
    }

    public void setIdscene(int idscene) {
        this.idscene = idscene;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }
    
}
