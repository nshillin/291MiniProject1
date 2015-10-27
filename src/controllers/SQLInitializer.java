package controllers;

import java.sql.*;

public class SQLInitializer {
/*
 * This class will not be submitted,
 * but is used to allow us to login separately for sqlplus.
 */
	// The URL we are connecting to
    private static String m_url = "jdbc:oracle:thin:@gwynne.cs.ualberta.ca:1521:CRS";

    // The driver to use for connection
    private static String m_driverName = "oracle.jdbc.driver.OracleDriver";
    
    private static String username;
    private static String password;
    
    private static Class drvClass;
    
    private static Connection connection;
    
    public static String SUCCESS_MESSAGE = "Success";

	// Logs user into SQLPlus
	public static String login(String newusername, String newpassword) {
		username = newusername;
		password = newpassword;
		
		try {
			drvClass = Class.forName(m_driverName);     
	    } 
		catch(Exception e) {
			return "ClassNotFoundException: " + e.getMessage();
		} 
		try {
			connection = DriverManager.getConnection(m_url, username, password);
			connection.close();
		} catch(Exception e) {
			return "SQLException: " + e.getMessage();
		}
		return SUCCESS_MESSAGE;
	}
	
	public static void logout() {
		try {
			connection.close();
		}
		catch (Exception e) {

		}
	}
	
	// Executes passed in queries
	public static ResultSet executeQuery(String query) {
		try {
			//Must re-establish the connection for every query otherwise a ConnectionClosed exception is thrown when creating a statement.
			connection = DriverManager.getConnection(m_url, username, password);
			Statement stmt = connection.createStatement(
					ResultSet.TYPE_SCROLL_SENSITIVE, 
					ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery(query);
			return rs;
		}
		catch (Exception e) 
		{
			String exception = e.getMessage();
		}
		return null;
	}
	
	// Closes the connection
	public static void closeConnection()
	{
		try{
			connection.close();
		} catch (Exception e){
			String mess = e.getMessage();
		}
	}
	
	// Executes any updates passed in
	public static void executeUpdate(String update) {
		try 
		{
			//Must re-establish the connection for every query otherwise a ConnectionClosed exception is thrown when creating a statement.
			connection = DriverManager.getConnection(m_url, username, password);
			Statement stmt = connection.createStatement();
		    stmt.executeUpdate(update);
		    stmt.close();
		    //connection.close();
		}
		catch (Exception e) {

		}
	}
	
	// Sets up connection to database
	public static Connection getDatabaseConnection()
	{
		try
		{
			return DriverManager.getConnection(m_url, username, password); 
		} 
		catch (SQLException ex)
		{
			return null;
		}
	}
}
