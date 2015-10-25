package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import models.Booking;
import models.User;

public class QueryHandler {
	

	public static void main(String[] args) {
		// Setup SQL stuff	
	}
	
	public static Boolean authenticateUser(String password) {
		// Checks SQL for user
		String username = User.getUser();
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

	public static void addUser(String password) {
		String username = User.getUser();
		
	}
	
	public static Boolean isUsername() {
		//Checks if username already exists
		String username = User.getUser();
		String query = "select email from users where email = " + username;
		ResultSet rs = SQLInitializer.executeQuery(query);
		try {
			while (rs.next())
			{
				 if (rs.getString("email").equals(username)) {
					 return true;
				 }
				
			}
		}
		catch (Exception e) {
			
		}
		return true;
	}
	
	public static Boolean isAirlineAgent() {
		String username = User.getUser();
		String query = "select email from airline_agents where email = " + username;
		ResultSet rs = SQLInitializer.executeQuery(query);
		try {
			while (rs.next())
			{
				 if (rs.getString("email").equals(username)) {
					 return true;
				 }
				
			}
		}
		catch (Exception e) {
			
		}
		return false;
	}
	
	public static LinkedList<Booking> getBooking() {
		String username = User.getUser();
		String query = "select email from airline_agents where email = " + username;
		ResultSet rs = SQLInitializer.executeQuery(query);
		LinkedList<Booking> bookingList = new LinkedList<Booking>();
		try {
			while (rs.next())
			{
				//tno, flightno, fare, dep_date, seat
				//ticket number, the passenger name, the departure date and the price
				Booking booking = new Booking();
				rs.getInt("tno");
				rs.getString("name");
				rs.getDate("dep_date");
				rs.getFloat("price");
				bookingList.add(booking);
			}
				
		}
		catch (Exception e) {
			
		}
		return bookingList;
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
