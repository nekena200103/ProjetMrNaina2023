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
@Table(name="personne")
@NomTable(nom="personne")
public class Personne {
     @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="idpersonne")
    @SequenceGenerator(name="idpersonne", sequenceName="idpersonne", allocationSize=1)
    @Attribut(columnName = "idpersonne",isprimarykey = true)
    int idpersonne;
     @Attribut(columnName = "nom")
    String  nom;

    public Personne() {
    }

    public Personne(int idpersonne, String nom) {
        this.idpersonne = idpersonne;
        this.nom = nom;
    }

    public int getIdpersonne() {
        return idpersonne;
    }

    public void setIdpersonne(int idpersonne) {
        this.idpersonne = idpersonne;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
     
     
}
