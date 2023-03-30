/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import HibernateDao.GenericDao;
import annotation.Attribut;
import annotation.NomTable;
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
@Table(name="Logback")
@NomTable(nom="Logback")
public class Logback {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="idlogback")
    @SequenceGenerator(name="idlogback", sequenceName="idlogback", allocationSize=1)
    @Attribut(columnName = "idlogback",isprimarykey = true)
    int iduser;
    @Attribut(columnName = "nom")
    String nom;
    @Attribut(columnName = "mdp")
    String mdp;

    public Logback() {
    }

    public Logback(String nom, String mdp) {
        this.iduser=-1;
        this.nom = nom;
        this.mdp = mdp;
    }
    

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    
}
