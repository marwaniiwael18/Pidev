/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trenna.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;





import trenna.common.RegexValidation;
import trenna.entities.Role;

import trenna.entities.User;
import trenna.entities.UserRole;
import trenna.exceptions.BadRequestException;
import trenna.utils.DBConnexion;
import trenna.utils.PasswordUtils;

/**
 *
 * @author moham
 */
public class UserService implements IService<User>{

    private Connection cnx;
    private static UserService instance;
    
    private static User currentUser;
    
    public UserService(){
        cnx =DBConnexion.getInstance().getCnx();
        
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        UserService.currentUser = currentUser;
    }


    
    
    
    public static UserService getInstance(){
        	if(instance == null) {
			instance = new UserService();
		}
			
		return instance;	
    }
    
    @Override
    public void ajouter(User u) {
        String salt = PasswordUtils.getSalt(30);
        String SecurePassword = PasswordUtils.generateSecurePassword(u.getMdp(), salt);
        String verificationCode = RandomStringUtils.randomNumeric(8);
        try {
            String querry="INSERT INTO `utilisateur`(`nom`, `prenom`,`email`,`age`,`mdp`,`role`, verification_code) "
                    + "VALUES ('"+u.getNom()+"','"+u.getPrenom()+"','"+u.getEmail()+"','"+u.getAge()+"','"+u.getMdp()+"',"
                    + "2,'"+verificationCode+"')";
            String sql = "Select * From utilisateur where email='"+u.getEmail()+"' ";
            Statement stm =cnx.createStatement();
        if (null != u.getMdp() && (u.getMdp().length()) < 8
	        || !RegexValidation.checkPasswordFormat(RegexValidation.CHAR_PATTERN, u.getMdp())
		|| !RegexValidation.checkPasswordFormat(RegexValidation.NUMERIC_PATTERN, u.getMdp())
		|| !RegexValidation.checkPasswordFormat(RegexValidation.NOT_CHAR_PATTERN, u.getMdp())) {
            System.err.println("");
            throw new BadRequestException("Mot de passe incorrect, il faut saisir au minimum "
                    + "une chiffre, un caratere et une lettre avec un totale de 8 au minimum svp ");
           
                
	}    
        if(!RegexValidation.isValidEmail(u.getEmail())){
//            System.err.println("le compte email est incorrect !! ");
            throw new BadRequestException("le compte email est incorrect !! ");
        }
        String duplicate = null ;
        ResultSet rs = stm.executeQuery(sql);
        while(rs.next()){
            duplicate = rs.getString(1);
            
        }
        if(duplicate == null){
            stm.executeUpdate(querry);
            System.out.println("Ajout avec succes");
        }else{
                System.err.println("le compte deja existe");  
        }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
        } catch (BadRequestException ex) {
            System.out.println(ex.getMessage()); 
        }
        UserRole userRole = new UserRole();
        UserRoleService userRoleService = new UserRoleService();
        Role role = new Role();
        role.setIdRole(2);
        
        User user = rechercherParEmail(u);
        userRole.setUser(u);
        userRole.setRole(role);
        userRoleService.ajouter(userRole);
    }

    @Override
    public List<User> afficher() {
         List<User> users = new ArrayList<>();
    
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT * FROM `utilisateur`";
     
            ResultSet rs= stm.executeQuery(querry);
        RoleService roleService = new RoleService();
        while(rs.next()){
            User u = new User();
            u.setId(rs.getInt("id"));
            u.setNom(rs.getString("nom"));
            u.setPrenom(rs.getString("prenom"));
            u.setEmail(rs.getString("email"));
            u.setAge(rs.getInt("age"));            
//            u.setMdp(rs.getString("mdp"));
            int idRole = rs.getInt(7);
            Role role = roleService.getById(idRole);
            u.setRole(role);
            
            users.add(u);
            System.out.println(u.toString()); 
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
         return  users;
    }

    @Override
    public Boolean update(User t) {
        boolean update= true;
        String query = "UPDATE `utilisateur` SET nom='"+t.getNom()+"', prenom='"+t.getPrenom()+"',"
                + " age='"+t.getAge()+"', email='"+t.getEmail()+"', mdp='"+t.getMdp()+"', role='"+t.getRole().getIdRole()+"' "
                + "WHERE id ='"+t.getId()+"'"; 
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
    public Boolean supprimer(User t) {
        boolean suppression = true;
        try {
            String query = "DELETE FROM `utilisateur` WHERE id ='"+t.getId()+"'";     
            Statement stm = cnx.createStatement();
            stm.executeUpdate(query);
            System.out.println("l'utilisateur a été bien supprimer");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
            suppression = false;
        }   
        return suppression;
       
    }
    
    public boolean login(User u){
        boolean login = false ;
        String sql = "SELECT email, mdp from `utilisateur`   WHERE email='" +u.getEmail()+ "' and mdp='" + u.getMdp() + "'";
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();  
            if(rs.next()){
                if((rs.getString("email").equals(u.getEmail()))
                        && (rs.getString("mdp").equals(u.getMdp()))){
                    System.out.println("Vous etes connecté(e)" );
                    
//                    setCurrentUser(u);
                    
                    login = true ;
                    
                }else
                    System.err.println("Veuillez saisir correctement l'adresse ou le mot de passe" );
//             System.out.println("Authentifiaction valide pour le client "+u.getEmail()+"");
//            if(u.getVerificationCode().equals(rs.getString("verification_code"))){
//                    System.out.println("Le compte est activé");
//                }
            }else
                System.err.println("le client "+u.getEmail()+" n'existe pas  "); 
                return login;
            
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return login;
    }
    public boolean activerAccountUser(User u){
        
         boolean active = true;
         
         String sql = "SELECT * from `utilisateur` WHERE verification_code='" +u.getVerificationCode()+ "'";
         try{
              PreparedStatement ps = cnx.prepareStatement(sql);
              ResultSet rs = ps.executeQuery();
              while(rs.next()){
                  u.setEmail(rs.getString("email"));
                  u.setMdp(rs.getString("mdp"));
                  rechercherParEmail(u);
              }
              
              
         }catch (SQLException ex) {
            System.err.println(ex.getMessage());
            active = false ;
        }
        return active;
        
    }

    public User rechercherParEmail(User t) {
        
        List<User> users = new ArrayList<>();
        RoleService roleService = new RoleService();
        String sql ="SELECT * FROM utilisateur WHERE email=? ";
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setString(1, t.getEmail());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                t.setId(rs.getInt(1));
                t.setNom(rs.getString(2));
                t.setPrenom(rs.getString(3));
                t.setAge(rs.getInt(4));
                t.setEmail(rs.getString(5));
                t.setMdp(rs.getString(6));
                int idRole = rs.getInt(7);
                Role role = roleService.getById(idRole);
                t.setRole(role);
                t.setVerificationCode(rs.getString(8));
                users.add(t);
                System.out.println(t.toString());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return t;
    }

    public User rechercherParName(User t) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM utilisateur WHERE nom=? and prenom=? "; 
        PreparedStatement ps;
        try {
            ps = cnx.prepareStatement(sql);
            ps.setString(1, t.getNom());
            ps.setString(2, t.getPrenom());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                t.setId(rs.getInt(1));
                t.setNom(rs.getString(2));
                t.setPrenom(rs.getString(3));
                t.setAge(rs.getInt(4));
                t.setEmail(rs.getString(5));
                t.setMdp(rs.getString(6));
//                t.setRole(rs.getString(7));
                users.add(t);
                System.out.println(t.toString());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         
        return t;
        
    }
    
    public User getUserById(int id){
        String sql = "SELECT utilisateur FROM utilisateur WHERE id=? ";
        PreparedStatement ps;
        User t = new User();
        try {
            ps = cnx.prepareStatement(sql);
            ps.setInt(1, id);        
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                t.setId(rs.getInt(1));
                t.setNom(rs.getString(2));
                t.setPrenom(rs.getString(3));
                t.setAge(rs.getInt(4));
                t.setEmail(rs.getString(5));
                t.setMdp(rs.getString(6));
//                t.setRole(rs.getString(7));
                System.out.println(t.toString());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         
        return t;
        
    }
    
    public void validateCurrentLogin() {
        
//
//        String verifyLogin = "SELECT count(1) FROM utilisateur WHERE email = '" + currentUser.getEmail() + "' AND mdp = '" + currentUser.getMdp() + "'";
//        
//         try {
//            Statement statement = cnx.createStatement();
//            ResultSet queryResult = statement.executeQuery(verifyLogin);
//
//            while (queryResult.next()) {
//                if (queryResult.getInt(1) == 1) {
//                    setCurrentUser(currentUser);
//                    
//                } else {
//                    System.out.println("l'utilisateur n'est pas connecté");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            e.getCause();
//        }
    }
}
