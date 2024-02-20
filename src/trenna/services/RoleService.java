/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trenna.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import trenna.entities.Role;
import trenna.utils.DBConnexion;
/**
 *
 * @author moham
 */
public class RoleService implements IService<Role>{

    private Connection cnx;
    
    public RoleService(){
        cnx =DBConnexion.getInstance().getCnx();
    } 
    
    @Override
    public void ajouter(Role r) {
        try {
            String querry="INSERT INTO `role`(`idRole`, `descriptipon`,`name`) "
                    + "VALUES ('"+r.getIdRole()+"','"+r.getDescription()+"','"+r.getName()+"')";
            Statement stm =cnx.createStatement();                       
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
        }
    }
    

    @Override
    public List<Role> afficher() {
   List<Role> roles = new ArrayList<>();
    
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT * FROM `role`";
     
            ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
            Role r = new Role();
            r.setIdRole(rs.getInt("idRole"));
            r.setDescription(rs.getString("description"));
            r.setName(rs.getString("name"));
          
            roles.add(r);
            System.out.println(r.toString()); 
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
         return  roles;
    }

    @Override
    public Boolean update(Role r) {
      boolean update= true;
        String query = "UPDATE `role` SET description='"+r.getDescription()+"', name='"+r.getName()+"'"
                + "WHERE idRole ='"+r.getIdRole()+"'"; 
        try {
            Statement stm = cnx.createStatement();
            stm.executeUpdate(query);
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            update = false;
        }
        return update;
    }

    @Override
    public Boolean supprimer(Role r) {
        boolean suppression = true;
        try {
            String query = "DELETE FROM `role` WHERE idRole ='"+r.getIdRole()+"'";     
            Statement stm = cnx.createStatement();
            stm.executeUpdate(query);
            System.out.println("le role a été bien supprimer");            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
            suppression = false;
        }   
        return suppression;
    }
    
    public Role getById(int id) {
        List<Role> roles = new ArrayList<>();
        Role r = new Role();
        String sql ="SELECT * FROM role WHERE idRole="+id;
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);        
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                r.setIdRole(rs.getInt(1));
                r.setDescription(rs.getString(2));
                r.setName(rs.getString(3));               
                roles.add(r);
                System.out.println(r.toString());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return r;
    }

    
    
    
}
