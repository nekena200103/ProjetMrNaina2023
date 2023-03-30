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
 * @author caeyla
 */
@Entity
@Table(name="auteur")
@NomTable(nom="auteur")
public class Acteur {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="idauteur")
    @SequenceGenerator(name="idauteur", sequenceName="idauteur", allocationSize=1)
    @Attribut(columnName = "idauteur",isprimarykey = true)
    int idauteur;
    @Attribut(columnName = "nom")
    String nom;
    @Attribut(columnName = "mdp")
    String mdp;

    public Acteur() {
        idauteur=-1;
    }
    
    public Acteur(String nom,String motdepasse) {
        idauteur=-1;
        this.mdp=motdepasse;
        this.nom=nom;
    }
    
    public int getIdauteur() {
        return idauteur;
    }

    public void setIdauteur(int idauteur) {
        this.idauteur = idauteur;
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
