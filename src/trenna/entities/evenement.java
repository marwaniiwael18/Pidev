/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trenna.entities;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author medom
 */
public class evenement {
   
   private int idEven;
    private String nom;
    private Date dateEven;
    private int prix;
    private int recompense;

    public evenement() {
    }

    public evenement(int idEven, String nom, Date dateEven, int prix, int recompense) {
        this.idEven = idEven;
        this.nom = nom;
        this.dateEven = dateEven;
        this.prix = prix;
        this.recompense = recompense;
    }

    public evenement(String nom, Date dateEven, int prix, int recompense) {
        this.nom = nom;
        this.dateEven = dateEven;
        this.prix = prix;
        this.recompense = recompense;
    }

    public int getIdEven() {
        return idEven;
    }

    public String getNom() {
        return nom;
    }

    public Date getDateEven() {
        return dateEven;
    }

    public int getPrix() {
        return prix;
    }

    public int getRecompense() {
        return recompense;
    }

    public void setIdEven(int idEven) {
        this.idEven = idEven;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDateEven(Date dateEven) {
        this.dateEven = dateEven;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setRecompense(int recompense) {
        this.recompense = recompense;
    }

    @Override
    public String toString() {
        return "evenement{" + "idEven=" + idEven + ", nom=" + nom + ", dateEven=" + dateEven + ", prix=" + prix + ", recompense=" + recompense + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final evenement other = (evenement) obj;
        if (this.idEven != other.idEven) {
            return false;
        }
        if (this.prix != other.prix) {
            return false;
        }
        if (this.recompense != other.recompense) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.dateEven, other.dateEven)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
