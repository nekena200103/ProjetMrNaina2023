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
@Table(name="plateau")
@NomTable(nom="plateau")
public class Plateau {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="idplateau")
    @SequenceGenerator(name="idplateau", sequenceName="idplateau", allocationSize=1)
    @Attribut(columnName = "idplateau",isprimarykey = true)
    int idplateau ;
    @Attribut(columnName = "intitule_plateau")
    String intitule_plateau;
    @Attribut(columnName = "posx")
    int posx;
    @Attribut(columnName = "posy")
    int posy;

    public Plateau( String intitule_plateau, int posx, int posy) {
        this.idplateau = -1;
        this.intitule_plateau = intitule_plateau;
        this.posx = posx;
        this.posy = posy;
    }

    public Plateau() {
    }

    public int getIdplateau() {
        return idplateau;
    }

    public void setIdplateau(int idplateau) {
        this.idplateau = idplateau;
    }

    public String getIntitule_plateau() {
        return intitule_plateau;
    }

    public void setIntitule_plateau(String intitule_plateau) {
        this.intitule_plateau = intitule_plateau;
    }

    

    public int getPosx() {
        return posx;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public int getPosy() {
        return posy;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }
    
    
}
