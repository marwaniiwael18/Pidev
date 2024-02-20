/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trenna.entities;

/**
 *
 * @author Amirov
 */
public class Arbitre {
       private int idArb;
       private String nomArb;
       private String specialite;
       private String email;

    public Arbitre() {
    }

    public Arbitre(int idArb, String nomArb, String specialite, String email) {
        this.idArb = idArb;
        this.nomArb = nomArb;
        this.specialite = specialite;
        this.email = email;
    }

    public Arbitre(String nomArb, String specialite, String email) {
        this.nomArb = nomArb;
        this.specialite = specialite;
        this.email = email;
    }

    public int getIdArb() {
        return idArb;
    }

    public void setIdArb(int idArb) {
        this.idArb = idArb;
    }

    public String getNomArb() {
        return nomArb;
    }

    public void setNomArb(String nomArb) {
        this.nomArb = nomArb;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Arbitre{" + "idArb=" + idArb + ", nomArb=" + nomArb + ", specialite=" + specialite + ", email=" + email + '}';
    }
    

    
       
       


}
