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
@Table(name="scene")
@NomTable(nom="scene")
public class Scene {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="idscene")
    @SequenceGenerator(name="idscene", sequenceName="idscene", allocationSize=1)
    @Attribut(columnName = "idscene",isprimarykey = true)
    int idscene;
    @Attribut(columnName = "idfilm")
    int idfilm;
    @Attribut(columnName = "description")
    String description;
    public Scene() {
        this.idscene = -1;
        
    }
    public Scene(int idscene, int idaction, int iddialogue, int idplateau, int idfilm, String description) {
        this.idscene = idscene;
        
        this.idfilm = idfilm;
        this.description = description;
    }

    public int getIdscene() {
        return idscene;
    }

    public void setIdscene(int idscene) {
        this.idscene = idscene;
    }

    

    public int getIdfilm() {
        return idfilm;
    }

    public void setIdfilm(int idfilm) {
        this.idfilm = idfilm;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
