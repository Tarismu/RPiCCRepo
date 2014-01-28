package de.tarismu.utils.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLAgent {

	private String host, user, password, database;
	private Integer port;
	private Statement stmt;
	
	/**
	 * Initiates a new MySQLAgent that is used for getting entrys and writing in database
	 * @param host
	 * @param port
	 * @param user
	 * @param database
	 * @param password
	 */
	
	public MySQLAgent(String host, Integer port, String user, String database, String password){
		this.host = host;
		this.user = user;
		this.password = password;
		this.database = database;
		this.port = port;
	}
	
	/**
	 * Gets all entrys in the logger database table an returns it in an ResultSet
	 * @return
	 */
	
	public ResultSet getEntrys(){
		
		ResultSet resultSet = null;
		
        try {
        	
            try {
                Class.forName("com.mysql.jdbc.Driver");
                
            } catch (ClassNotFoundException e) {
                System.out.println("Klasse nicht gefunden");
            }

            String url = "jdbc:mysql://" + host + ":" + port + "/" + database;       
            Connection con = DriverManager.getConnection(url, user, password);        
            stmt = con.createStatement();
            
            resultSet = stmt.executeQuery("SELECT `ID`,`temp` FROM `temperaturelog` WHERE 1");
            
            return resultSet;
            
	    } catch (SQLException ex) {
	        System.out.println("Couldn't connect database: " + ex.getLocalizedMessage());
	    }
	    
	    return resultSet;
		
	}
	
}
