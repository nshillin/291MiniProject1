package controllers;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import org.eclipse.swt.widgets.Shell;
import models.Booking;
import models.User;
import views.BookingView;
import views.LoginView;
import views.MainView;

import static java.lang.System.*;

public class QueryHandler {
	

	public static void main(String[] args) {
		// Setup SQL stuff	
	}
	
	public static Boolean authenticateUser(String password) {
		// Checks SQL for user
		String username = User.getUser();
		String query = "select pass from users where email = '"+ username + "'";
		ResultSet rs = SQLInitializer.executeQuery(query);
		try {
			while (rs.next())
			{
				LoginView.errorMessage(rs.getString("pass"));
				 if (rs.getString("pass").equals(password)) {
					 return true;
				 }
				
			}
			rs.close();
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
		String query = "select email from users where email = '" + username + "'";
		ResultSet rs = SQLInitializer.executeQuery(query);
		try {
			while (rs.next())
			{
				 if (rs.getString("email").equals(username)) {
					 
					 return true;
				 }
				
			}
			rs.close();
		}
		catch (Exception e) {
			
		}
		return true;
	}
	
	public static Boolean isAirlineAgent() {
		String username = User.getUser();
		String query = "select email from airline_agents where email = '" + username + "'";
		ResultSet rs = SQLInitializer.executeQuery(query);
		try {
			while (rs.next())
			{
				 if (rs.getString("email").equals(username)) {
					 return true;
				 }
				
			}
			rs.close();
		}
		catch (Exception e) {
			
		}
		return false;
	}
	
	public static int newTicketNo()
	{
		//Generates a new unique ticket number by incrementing the current maximum by 1.
		String query = "select max(tno) + 1 as tnonew "
				+ "from tickets";
		ResultSet rs = SQLInitializer.executeQuery(query);
		try {
			if (rs.next())
			{
				return rs.getInt("tnonew");
			}
			rs.close();
		}
		catch (Exception e) {
			
		}
		return 0;
	}
	
	public static LinkedList<Booking> getBookings() {
		String username = User.getUser();
		String query = "select bookings.tno, flightno, fare, dep_date, seat, name, paid_price"
				+ "from bookings, tickets"
				+ "where bookings.tno = tickets.tno"
				+ "and email = '" + username + "'";
		ResultSet rs = SQLInitializer.executeQuery(query);
		LinkedList<Booking> bookingList = new LinkedList<Booking>();
		try {
			while (rs.next())
			{
				//tno, flightno, fare, dep_date, seat
				//ticket number, the passenger name, the departure date and the price
				Booking booking = new Booking();
				booking.setTicketNumber(rs.getInt("tno"));
				booking.setpName(rs.getString("name"));
				booking.setFare(rs.getString("fare"));
				booking.setFlightNumber(rs.getString("flightno"));
				booking.setSeat(rs.getString("seat"));
				booking.setDepDate(rs.getDate("dep_date"));
				booking.setPrice(rs.getFloat("paid_price"));
				bookingList.add(booking);
			}
			rs.close();
				
		}
		catch (Exception e) {
			
		}
		return bookingList;
	}
	
	public static Boolean isSeatAvailable(String flightno, Date depDate, String seat)
	{
		String query = "SELECT COUNT(tno) AS num FROM bookings "
				+ "WHERE flightno = " + flightno
				+ " AND dep_date = " + depDate
				+ " AND seat = " + seat;
		ResultSet rs = SQLInitializer.executeQuery(query);
		try {
			if (rs.next())
			{
				return (rs.getInt("num") == 0);
			}
			rs.close();
		}
		catch (Exception e) {
			
		}
		return false;
	}
	
	public static Boolean isFareAvailable(String flightno, String fare)
	{
		int limit = 0;
		int numBookings = 0;
		String query = "SELECT limit FROM flight_fares "
				+ "WHERE flightno = " + flightno
				+ " AND fare = " + fare;
		ResultSet rs = SQLInitializer.executeQuery(query);
		try {
			if (rs.next())
			{
				limit = rs.getInt("limit");
			}
			rs.close();
		}
		catch (Exception e) { }
		query = "SELECT COUNT(tno) AS numBookings FROM bookings "
				+ "WHERE flightno = " + flightno
				+ " AND fare = " + fare;
		try {
			if (rs.next())
			{
				numBookings = rs.getInt("numBookings");
			}
			rs.close();
		}
		catch (Exception e) { }
		return (limit - numBookings > 0);
	}
	
	public static void removeBooking(Booking booking, Shell shell) {
		try {
			Integer tno = booking.getTicketNumber();
			String update = "DELETE FROM Bookings "
					+ "where tno = '" + tno.toString() + "'";
			SQLInitializer.executeUpdate(update);
			
			update = "DELETE FROM Tickets "
					+ "where tno = " + tno.toString();
			SQLInitializer.executeUpdate(update);
		} catch (Exception e) {
			
		}
		LoginController.bookingView(shell);
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
			rs.close();
		}
		catch (Exception e) {
			
		}
 	}
}
