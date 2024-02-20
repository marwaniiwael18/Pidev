/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trenna.entities;

import java.sql.Date;

/**
 *
 * @author medom
 */
public class Commentaire {
      private int id;
    private int id_guest;
    private int id_even,note;
    private String nom;
    private String comment;
    private Date date_com;

    public Commentaire() {
    }

    public Commentaire(int id, int id_guest, int id_even, int note, String nom, String comment, Date date_com) {
        this.id = id;
        this.id_guest = id_guest;
        this.id_even = id_even;
        this.note = note;
        this.nom = nom;
        this.comment = comment;
        this.date_com = date_com;
    }

    public Commentaire(int id_guest, int id_even, int note, String nom, String comment, Date date_com) {
        this.id_guest = id_guest;
        this.id_even = id_even;
        this.note = note;
        this.nom = nom;
        this.comment = comment;
        this.date_com = date_com;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    

   

    public int getId() {
        return id;
    }

    public int getId_guest() {
        return id_guest;
    }

    public int getId_even() {
        return id_even;
    }

    

    public String getNom() {
        return nom;
    }

    public String getComment() {
        return comment;
    }

    public Date getDate_com() {
        return date_com;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_guest(int id_guest) {
        this.id_guest = id_guest;
    }

    public void setId_even(int id_even) {
        this.id_even = id_even;
    }

   

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDate_com(Date date_com) {
        this.date_com = date_com;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", id_guest=" + id_guest + ", id_even=" + id_even + ", nom=" + nom + ", comment=" + comment + ", date_com=" + date_com + '}';
    }

   

    
    

  

   
    
    
    
}
