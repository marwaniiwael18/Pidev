/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trenna.entities;

import java.util.HashSet;
import java.util.Set;

public class UserSession {
 
    private UserSession instance;
 
    private static String userName;
    private static int idUser;
    private static Set<String> privileges;
 
    private UserSession(String userName, int idUser, Set<String> privileges) {
        UserSession.userName = userName;
        UserSession.idUser = idUser;
        UserSession.privileges = privileges;
    }
 
    /**
    *
    * @param userName
    * @param employeeId
    * @param privileges
    * @return
    */
    public UserSession getInstance(String userName, int employeeId, Set<String> privileges) {
        if(instance == null) {
            instance = new UserSession(userName, employeeId, privileges);
        }
        return instance;
    }
 
    public String getUserName() {
        return userName;
    }

    public static int getIdUser() {
        return idUser;
    }
         
    
         
    public Set<String> getPrivileges() {
        return privileges;
    }
 
    public void cleanUserSession() {
        userName = "";// or null
        idUser = 0;
        privileges = new HashSet<>();// or null
    }
 
    @Override
    public String toString() {
        return "UserSession{" +
                "userName='" + userName + '\'' +
                "employeeId = '" + idUser + '\'' +
                ", privileges=" + privileges +
            '}';
    }
 
    static class cleanUserSession {
 
        public cleanUserSession() {
            userName = "";// or null
            idUser = 0;
            privileges = new HashSet<>();// or null
        }
    }
}

