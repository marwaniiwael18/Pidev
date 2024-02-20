/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trenna.utils;

import java.sql.*;  


/**
 *
 * @author moham
 */
public class DBConnexion {
    // JDBC driver name and database URL
	public final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public final String url = "jdbc:mysql://localhost:3306/trenna-2";

	// Database credentials
	static final String login = "root";
	static final String pwd = "";
        Connection connexion;
	public static DBConnexion instance;

       private DBConnexion(){
            try {
            Class.forName(JDBC_DRIVER);
            connexion =DriverManager.getConnection(url, login, pwd);
            System.out.println("Cnx etablie ...");
            } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("ERROOOOOOOOOOR");
            
        }
    }
	public static DBConnexion getInstance(){
        	if(instance == null) {
			instance = new DBConnexion();
		}
			
		return instance;	
	}
        public Connection getCnx(){
            return connexion;
        }
}



