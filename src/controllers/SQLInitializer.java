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
	
	public static void main(String[] args) {
        

        /*
        String createString;
        
     // SQL statement to execute
        createString = "create table TOFFEES " +
                 "(T_NAME VARCHAR(32) PRIMARY KEY, " +
                 "SUP_ID INTEGER, " +
                 "PRICE FLOAT, " +
                 "SALES INTEGER, " +
                 "TOTAL INTEGER)";

        // create a statement object
       // Statement stmt;
     
     
        */
/*
       try
       {
              Class drvClass = Class.forName(m_driverName);
       } catch(Exception e)
       {

              System.err.print("ClassNotFoundException: ");
              System.err.println(e.getMessage());
       } 
       
       try
       {
              // Establish a connection

              m_con = DriverManager.getConnection(m_url, m_userName,
              m_password);

              // Changed to reflect changes made in the result set and to make these changes permanent to the database too
              Statement stmt = m_con.createStatement(
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
              // Since it is a DML command, use executeUpdate. Automatically converts our string to an SQL command.
              // Notioce: There is no ; in the query
              stmt.executeUpdate("drop table toffees");
              stmt.executeUpdate(createString);

              // insert a row
              createString = "insert into toffees values ('Quadbury', 101,7.99,0,0)";
              stmt.executeUpdate(createString);

              // Suppose executing a query and printing the results
              String query = "select T_NAME, SUP_ID, SALES, PRICE, TOTAL from toffees";
              // when you want to use an updatable result set, you cannot use * for select all:
              // all column names should be specified.
              ResultSet rs = stmt.executeQuery(query);
              while (rs.next())
              {
                String s = rs.getString("T_NAME");
                int supid = rs.getInt("SUP_ID");
                float n = rs.getFloat("PRICE");
                int sales = rs.getInt("SALES");
                int total = rs.getInt("TOTAL");
                System.out.println(s + "," + supid+"," +sales+"," +n+"," +total);
              }
              System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
              // Updating one of the rows
              // Suppose we want to update 'Quadbury' to 'Cadbury'
              rs.first(); // it the first row we want to update
              rs.updateString(1,"Cadbury"); // currently only in the result set: Indexing from 1.
              rs.updateRow(); // makes the changes permanent

              // Now let's add a new row without using the insert command
              rs.moveToInsertRow(); // move to the end
              // for every column use updateX() method: indexing from 1. You may use the column name instead of index.
              rs.updateString(1,"Jewel");
              rs.updateInt(2,105);
              rs.updateDouble(3,4.99);
              rs.updateInt(4,3);
              rs.updateInt(5,2);
              rs.insertRow(); // make it permanent.

              // See what we did!
              rs = stmt.executeQuery(query);
             
              // No more statements to compile/execute. So, close connection.
              stmt.close();
              m_con.close();

              System.out.println("Successful!");

       } catch(SQLException ex) {

    	   	System.err.println("SQLException: " +
    	   	ex.getMessage());

       }
       */
                  
	}
	
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
	
	public static void executeUpdate(String update) {
		try 
		{
			//Must re-establish the connection for every query otherwise a ConnectionClosed exception is thrown when creating a statement.
			connection = DriverManager.getConnection(m_url, username, password);
			Statement stmt = connection.createStatement();
		    stmt.executeUpdate(update);
		    stmt.close();
		}
		catch (Exception e) {

		}
	}
	
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
