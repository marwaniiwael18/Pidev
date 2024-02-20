/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trenna.services;

import trenna.entities.Commentaire;
import trenna.entities.evenement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;
import trenna.utils.DBConnexion;

/**
 *
 * @author medom
 */
public class CommentaireService implements IService1<Commentaire> {
   private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    private Connection conn;

    public CommentaireService() {
        conn = DBConnexion.getInstance().getCnx();
    }
    
    public void ajouterCommentaire(Commentaire e){
        String req = "insert into commentaire (id,id_guest,id_even,nom,comment,date_com,note) values (?,?,?,?,?,?,?)";
     

        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, e.getId());
            pst.setInt(2, e.getId_guest());
            pst.setInt(3, e.getId_even());
            pst.setString(4, e.getNom());
            pst.setString(5, e.getComment());
            pst.setDate(6, e.getDate_com());
            pst.setInt(7,e.getNote());
            
            pst.executeUpdate();
            System.out.println("le commentaire a été bien ajouter ");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
     
    
     
     

    @Override
    public void insert(Commentaire t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Commentaire> readAll() {
       String req = "select * from commentaire";

        List<Commentaire> commentaire=new ArrayList<>();
        try {
            ste = conn.createStatement();
           rs= ste.executeQuery(req);
           while(rs.next()){
               Commentaire e = new Commentaire();
               e.setId(rs.getInt("id"));
               e.setId_guest(rs.getInt("id_guest"));
               e.setId_even(rs.getInt("id_even"));
               e.setNom(rs.getString("nom"));
               e.setComment(rs.getString("comment"));
               e.setDate_com(rs.getDate("date_com"));
               commentaire.add(e);
               System.out.println(e.toString());
           }

        } catch (SQLException ex) {
           System.out.println(ex.getMessage()); ;
        }
        return commentaire;
    }

    @Override
    public boolean modifier(Commentaire t) {
          boolean update= true;
        String query = "UPDATE Commentaire SET id_guest='"+t.getId_guest()+"',"
                + "id_even='"+t.getId_even()+"', nom='"+t.getNom()+"', comment='"+t.getComment()+"', date_com='"+t.getDate_com()+"' "
                + "WHERE id ='"+t.getId()+"'"; 
        try {
            Statement stm = conn.createStatement();
            stm.executeUpdate(query);
            System.out.println("le commentaire a été bien modifier");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            update = false;
        }
        return update;
        
        
        
    }

    @Override
    public void supprimer(int id) {
       try {
            String sql = "DELETE FROM commentaire WHERE id=?;";
            
            pst=conn.prepareStatement(sql);
			
			pst.setInt(1,id);
			
			int executeUpdate = pst.executeUpdate();
			
			if(executeUpdate ==1){
				System.out.println("Commentaire supprimé avec ID::"+id);
			}
		} catch (SQLException e) {
		}
	
	}
    
    
    public List<Commentaire> afficherCommentairee(int id){
        List<Commentaire> commentaires = new ArrayList<>();
        String sql="select * from commentaire where id_even='" + id + "'";
        try {
            pst=conn.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                Commentaire e = new Commentaire();
               e.setId(rs.getInt("id"));
               e.setId_guest(rs.getInt("id_guest"));
               e.setId_even(rs.getInt("id_even"));
               e.setNom(rs.getString("nom"));
               e.setComment(rs.getString("comment"));
               e.setDate_com(rs.getDate("date_com"));
               commentaires.add(e);
                
                
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return commentaires;
    }
    
    public int GEtMoyRating(int id) {  

        int i = 0;
        try {
            String sqlStationName = " select AVG(note) as moyenne from commentaire where id_even="+id;
            Statement st3 = DBConnexion.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(sqlStationName);
            while (rs.next()) {

                i = (int)rs.getDouble("moyenne");
              //  System.out.println("i= "+i);

            }

            rs.close();
            st3.close();

        } catch (SQLException ex) {
            System.err.println("ERR" + ex);
        }
        return i;
    }
    
    
    public void modifierCommentaire(Commentaire p, int id){
        try {
            String sql = "UPDATE commentaire SET nom=?,comment=?,date_com=?,note=? WHERE id=?";
            
            pst=conn.prepareStatement(sql);
            pst.setString(1,p.getNom());
            pst.setString(2, p.getComment());
            pst.setDate(3, p.getDate_com());
            pst.setInt(4, p.getNote());
            pst.setInt(5, id);
           
           
            
            
            pst.executeUpdate();
            System.out.println("commentaire modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    } 
    
}
    
    
    
    
    
   






    
   


     
     




