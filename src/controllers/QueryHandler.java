package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryHandler {

	public static void main(String[] args) {
		// Setup SQL stuff	
	}
	
	public static Boolean authenticateUser(String username, String password) {
		// Checks SQL for user
		String query = "select pass from users where email = " + username;
		ResultSet rs = SQLInitializer.executeQuery(query);
		try {
			while (rs.next())
			{
				 if (rs.getString("pass").equals(password)) {
					 return true;
				 }
				
			}
		}
		catch (Exception e) {
			
		}
		return false;
	}

	public static void addUser(String username, String password) {
		
	}
	
	public static Boolean isUsername() {
		//Checks if username already exists
		return true;
	}
	
	public static Boolean isAirlineAgent(String username) {
		return true;
	}
	
	public static void exampleQuery() {
		String query = "select T_NAME, SUP_ID, SALES, PRICE, TOTAL from toffees";
		ResultSet rs = SQLInitializer.executeQuery(query);
		try {
			while (rs.next())
			{
				String s = rs.getString("T_NAME");
				int supid = rs.getInt("SUP_ID");
				float n = rs.getFloat("PRICE");
				int sales = rs.getInt("SALES");
				int total = rs.getInt("TOTAL");
				System.out.println(s + "," + supid+"," +sales+"," +n+"," +total);
			}
		}
		catch (Exception e) {
			
		}
 	}
}
