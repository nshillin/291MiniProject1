package controllers;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import org.eclipse.swt.widgets.Shell;
import models.Booking;
import models.Flight;
import models.Sch_Flight;
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
        String query = "insert into users values ('" + username +  "', '" + password + "' , SYSDATE)";
		SQLInitializer.executeQuery(query);
	}
	
	public static Boolean isUsername() {
		//Checks if username already exists
		String username = User.getUser();
		String query = "select email from users where email = '" + username + "'";
		ResultSet rs = SQLInitializer.executeQuery(query);
		try {
			while (rs.next())
			{
				return true;	
			}
			rs.close();
		}
		catch (Exception e) {
			
		}
		return false;
	}
	
	public static Boolean isAirlineAgent() {
		String username = User.getUser();
		String query = "select email from airline_agents where email = '" + username + "'";
		ResultSet rs = SQLInitializer.executeQuery(query);
		try {
			while (rs.next())
			{
				 return true;
				
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
		String query = "select bookings.tno, flightno, fare, dep_date, seat, name, paid_price "
				+ "from bookings, tickets "
				+ "where bookings.tno = tickets.tno "
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
	
	public static void updateLastLogin() {
		String update = "UPDATE users SET last_login = SYSDATE "
				+ "WHERE email = '" + User.getUser() + "'";
		SQLInitializer.executeQuery(update);
	}
	
	public static void setPassenger(String name, String country) {
        String query = "insert into passengers values ('" + User.getUser() +  "', '" + name + "' , '" + country + "')";
		SQLInitializer.executeQuery(query);
	}
	
	public static Integer setTicket(String name, float paid_price) {
		//TODO: Write this
		Integer newTicketNo = newTicketNo();
        String query = "insert into tickets values (" + newTicketNo +  ", '" + name + "' , '" + User.getUser() + "' , '" + paid_price + "')";
		SQLInitializer.executeQuery(query);
		return newTicketNo;
	}
	
	public static Boolean isFlightAvailable(Flight f)
	{
		//TODO: Write this
		return false;
	}
	
	public static LinkedList<Sch_Flight> getSingleFlights() {
		String username = User.getUser();
		String query = "select * from sch_flights";
		ResultSet rs = SQLInitializer.executeQuery(query);
		LinkedList<Sch_Flight> flightList = new LinkedList<Sch_Flight>();
		try {
			while (rs.next())
			{
				// flightno, dep_date, act_dep_time, act_arr_time
				Sch_Flight flight = new Sch_Flight();
				flight.setFlightNumber(rs.getString("flightno"));
				flight.setDepartureDate(rs.getDate("dep_date"));
				flight.setAct_arr_time(rs.getDate("act_arr_time"));
				flight.setAct_dep_time(rs.getDate("act_dep_time"));
				flightList.add(flight);
			}
			rs.close();
				
		}
		catch (Exception e) {
			
		}
		return flightList;
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

	public static void updateFlight(Sch_Flight flight) {
		// TODO Auto-generated method stub
		
	}
}
