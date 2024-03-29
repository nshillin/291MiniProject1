package controllers;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
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
	
	// Checks if user is in the system
	public static Boolean authenticateUser(String password) {
		// Checks SQL for user
		String username = User.getUser();
		String query = "select pass from users where email = '"+ username + "'";
		ResultSet rs = SQLInitializer.executeQuery(query);
		try {
			while (rs.next())
			{	
				// Requires testing
				String sqlpassword = rs.getString("pass").replaceAll("[\uFEFF-\uFFFF]", ""); 
				 if (sqlpassword.equals(password)) {
					 return true;
				 }
				
			}
			rs.close();
		}
		catch (Exception e) {
			
		}
		return false;
	}

	// Adds user to the system
	public static void addUser(String password) {
		String username = User.getUser();
        String query = "insert into users values ('" + username +  "', '" + password + "' , SYSDATE)";
		SQLInitializer.executeQuery(query);
	}
	
	// Checks if the username already exists
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
	
	// Checks if user is an airline agent
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
	
	// Creates a new ticket number
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
	
	// Retrieves all bookings made by user
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
	
	// Removes a particular booking
	public static void removeBooking(Booking booking, Shell shell) {
		try {
			Integer tno = booking.getTicketNumber();
			String update = "DELETE FROM Bookings "
					+ "where tno = " + tno.toString();
			SQLInitializer.executeUpdate(update);
			SQLInitializer.closeConnection();
			
			update = "DELETE FROM Tickets "
					+ "where tno = " + tno.toString();
			SQLInitializer.executeUpdate(update);
		} catch (Exception e) {
			
		}
		LoginController.bookingView(shell);
	}
	
	// Updates the user's last login
	public static void updateLastLogin() {
		String update = "UPDATE users SET last_login = SYSDATE "
				+ "WHERE email = '" + User.getUser() + "'";
		SQLInitializer.executeQuery(update);
	}
	
	// Creates a new passenger, provided they do not already exist
	public static void setPassenger(String name, String country) {
		String query = "select * from passengers where name = '" + name + "' and email = '" + User.getUser() + "'";
		String update = "insert into passengers values ('" + User.getUser() +  "', '" + name + "' , '" + country + "')";
		ResultSet rs = SQLInitializer.executeQuery(query);
		try {
			if (!rs.next()) {
				SQLInitializer.executeQuery(update);
			}
		} catch (Exception e) { }
	}
	
	// Creates a new ticket
	public static Integer setTicket(String name, float paid_price) {
		Integer newTicketNo = newTicketNo();
        String query = "insert into tickets values (" + newTicketNo +  ", '" + name + "' , '" + User.getUser() + "' , '" + paid_price + "')";
		SQLInitializer.executeQuery(query);
		return newTicketNo;
	}
	
	// Checks if a flight is available
	public static Boolean isFlightAvailable(Flight f)
	{
		String queryPart1 = "select ff.limit-count(tno) as available_seats from flight_fares ff, sch_flights sf, bookings b"
				+ " where sf.flightno = ff.flightno and sf.flightno = b.flightno and sf.flightno = '";
		String queryPart2 = "' and ff.fare = b.fare and sf.dep_date = b.dep_date"
				+ " and extract(day from sf.dep_date) = " + f.getDepDate_Cal().get(Calendar.DAY_OF_MONTH)
				+ " and extract(month from sf.dep_date) = " + (f.getDepDate_Cal().get(Calendar.MONTH) + 1)
				+ " and extract(year from sf.dep_date) = " + f.getDepDate_Cal().get(Calendar.YEAR)
				+ " group by ff.limit having ff.limit-count(tno) > 0";
		String finalquery;
		for (String flightno : f.getFlightNums())
		{
			finalquery = queryPart1 + flightno + queryPart2;
			ResultSet rs = SQLInitializer.executeQuery(finalquery);
			try {
				if (!rs.next())
				{
					return false;
				}
			}
			catch(Exception e) { }
		}
		return true;
	}
	
	// Returns a list of all flights
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
	
	// Creates a new booking
	public static void setBooking(int ticketNo, Flight f) {
		for (int i = 0; i < f.getFlightNums().size(); i++)
		{
			String query = "INSERT INTO bookings VALUES (?, ?, ?, ?, NULL)";
				PreparedStatement ps = SQLInitializer.prepareStatement(query);
				try {
					ps.setInt(1, ticketNo);
					String flightno = f.getFlightNums().get(i);
					ps.setString(2, flightno);
					String fare = f.getFare().get(i);
					ps.setString(3, fare);
					Date depDate = f.getDepDate();
					ps.setDate(4, depDate);
					ps.executeUpdate();
				}
				catch (SQLException e)
				{
				}
		}
	}
	
	// Example query for demonstrating setup
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

	// Updates a flight's act_arr_time and act_dep_time
	public static void updateFlight(Sch_Flight flight) {
		String query = "INSERT INTO sch_flights"
				+ " SET "
				+ "act_dep_time = to_date('"
				+ flight.getAct_dep_time().toString()
				+ "', 'yyyy-mm-dd) ,"
				+ "act_arr_time = to_date('"
				+ flight.getAct_arr_time().toString()
				+ "', 'yyyy-mm-dd) "
				+ "where flightno = '" + flight.getFlightNumber() + "' ";
		try {
			PreparedStatement preparedStatement = SQLInitializer.connection.prepareStatement(query);
			preparedStatement.setDate(1, flight.getAct_dep_time());
			preparedStatement.setDate(2, flight.getAct_arr_time());
			SQLInitializer.executeQuery(query);
		}
		catch (Exception e) {}
	}
}
