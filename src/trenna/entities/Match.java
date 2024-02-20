/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trenna.entities;

import java.sql.Date;



/**
 *
 * @author Amirov
 */
public class Match {
    private int idMatch;
    private String typeMatch;
    private Date dateMatch;
    private int idArb;


    public Match() {
    }

    public Match(int idMatch, String typeMatch, Date dateMatch, int idArb) {
        this.idMatch = idMatch;
        this.typeMatch = typeMatch;
        this.dateMatch = dateMatch;
              this.idArb = idArb;

          
    }

    public Match( String typeMatch, Date dateMatch,int idArb) {
        this.typeMatch = typeMatch;
        this.dateMatch = dateMatch;
            this.idArb = idArb;

            
    }

    public Match(int idMatch, String typeMatch, int idArb) {
        this.idMatch = idMatch;
        this.typeMatch = typeMatch;
        this.idArb = idArb;
    }
    

    

    public int getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(int idMatch) {
        this.idMatch = idMatch;
    }

    public int getIdArb() {
        return idArb;
    }

    public void setIdArb(int idArb) {
        this.idArb = idArb;
    }

    public String getTypeMatch() {
        return typeMatch;
    }

    public void setTypeMatch(String typeMatch) {
        this.typeMatch = typeMatch;
    }

    public Date getDateMatch() {
        return dateMatch;
    }

    public void setDateMatch(Date dateMatch) {
        this.dateMatch = dateMatch;
    }

    @Override
    public String toString() {
        return "Match{" + "idMatch=" + idMatch + ", typeMatch=" + typeMatch + ", dateMatch=" + dateMatch + ", idArb=" + idArb + '}';
    }

   
    

   
    
}
