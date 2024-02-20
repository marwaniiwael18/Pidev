/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trenna.services;
import trenna.entities.Match;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import trenna.utils.DBConnexion;
/**
 *
 * @author Amirov
 */
 public class ServiceMatch implements IService_1<Match>{
private Connection cnx ;
 
public ServiceMatch() {
    cnx = DBConnexion.getInstance().getCnx();
}

    @Override
    public void ajouter(Match m) {
        try {
             String querry="INSERT INTO `match`(`typeMatchh`, `dateMatchh`, `idArb` ) VALUES ('"+m.getTypeMatch()+"','"+m.getDateMatch()+"','"+m.getIdArb()+"')";
            Statement stm =cnx.createStatement();
        
        stm.executeUpdate(querry);
        System.out.println("Match ajouter !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
        }
        
    
    }

@Override
    public List<Match> afficher() {
        List<Match> matchs = new ArrayList();
    
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT * FROM `match`";
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
            Match m = new Match();
            m.setIdMatch(rs.getInt(1));
            m.setTypeMatch(rs.getString("typeMatchh"));
            m.setDateMatch(rs.getDate(3));
            m.setIdArb(rs.getInt(4));

            
            
            matchs.add(m);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
        return matchs;
   
    }
    
    
    

    @Override
    public void modifer(Match m) {
        try {
            String querry = "UPDATE `match` SET `idMatchh`='" + m.getIdMatch() +"', `typeMatchh`='" + m.getTypeMatch() + "' , `idArb`='" + m.getIdArb() + "' WHERE idMatchh=" + m.getIdMatch();
            Statement stm =cnx.createStatement();
            stm.executeUpdate(querry);
            System.out.println("La commande est modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    
    
 }

    public void supprimermatch(int idMatchh) {
        try {
            String querry = "DELETE FROM `match` WHERE idMatchh="+idMatchh;
            Statement stm =cnx.createStatement();
            stm.executeUpdate(querry);
            System.out.println("Commande supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }

    @Override
    public void supprimer(Match m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
   
       public Match FindBy(int idMatchh){
      Match m = new Match();
      try {
            Statement stmt = cnx.createStatement();
            String sql = "SELECT * FROM `match` where idMatchh ="+idMatchh;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                 
    
              //  int id = rs.getInt("id");
                m.setIdMatch(rs.getInt("idMatchh"));
                m.setTypeMatch(rs.getString("typeMatchh"));
                m.setDateMatch(rs.getDate("dateMatchh"));
           
               
                
                
                
            }
            rs.close();
            } 
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
      return m;
  }

    @Override
     public List<Match> sortByDate(){
         List<Match> users=afficher();
         List<Match> resultat=users.stream().sorted(Comparator.comparing(Match::getDateMatch)).collect(Collectors.toList());
         return resultat;
     }
       
      
       
}

    
    

    
 
