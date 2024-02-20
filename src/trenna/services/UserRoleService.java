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
import trenna.entities.User;

import trenna.entities.UserRole;
import trenna.utils.DBConnexion;

/**
 *
 * @author moham
 */
public class UserRoleService {
     private Connection cnx;
     
     public UserRoleService(){
        cnx =DBConnexion.getInstance().getCnx();
    }

  
    public void ajouter(UserRole ur) {
        try {
            String querry="INSERT INTO `user_role`(`idUser`, `idRole`) "
                    + "VALUES ('"+ur.getUser().getId()+"','"+ur.getRole().getIdRole()+"')";
            Statement stm =cnx.createStatement();  
            stm.executeUpdate(querry);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
        }
    }

  
    public List<UserRole> afficher() {
        List<UserRole> userRoles = new ArrayList<>();
    
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT * FROM `user_role`";
     
            ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
            UserRole ur = new UserRole();
           
//            ur.setUser(rs.getRow(1, User));
//            ur.setRole((Role) rs.getInt("idRole"));          
            userRoles.add(ur);
            System.out.println(ur.toString()); 
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
         return  userRoles;
    }

 
    public Boolean update(UserRole r) {
         boolean update= true;
        String query = "UPDATE `uer_role` SET idRole='"+r.getRole().getIdRole()+"'"
                + "WHERE idRole ='"+r.getUser().getId()+"'"; 
        try {
            Statement stm = cnx.createStatement();
            stm.executeUpdate(query);
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            update = false;
        }
        return update;
    }
    public Boolean supprimer(UserRole r) {
        boolean suppression = true;
        try {
            String query = "DELETE FROM `user_role` WHERE idUser ='"+r.getUser().getId()+"'";     
            Statement stm = cnx.createStatement();
            stm.executeUpdate(query);
            System.out.println("le role d'utilisateur a été bien supprimer");            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
            suppression = false;
        }   
        return suppression;
    }
    public UserRole getById(int id) {
        List<UserRole> roles = new ArrayList<>();
        UserRole r = new UserRole();
        RoleService roleService = new RoleService();
        UserService userService = new UserService();
        String sql ="SELECT * FROM user_role WHERE idUser="+id;
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);        
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int idUser = rs.getInt(1);
                int idRole = rs.getInt(2);
                Role role = roleService.getById(idRole);
                User user = userService.getUserById(idUser);
                r.setUser(user);
                r.setRole(role);
                roles.add(r);
                System.out.println(r.toString());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return r;
    }

    
}
