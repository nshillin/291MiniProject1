	package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import controllers.SQLInitializer;

public class FlightSearch {
	
	private static FlightSearch instance = null;
	private String airportCode;
	private Calendar departureDate;
	private String departureCity;
	private String arrivalCity;
	private Boolean roundTrip;
	private Boolean sortByConnections;
	
	/**
	 * Empty constructor.
	 */
	protected FlightSearch()
	{
	}
	
	/**
	 * Singleton Instance retrieval for a FlightSearch object.
	 * @return
	 */
	public static FlightSearch getInstance()
	{
		if(instance == null)
		{
			instance = new FlightSearch();
		}
		return instance;
	}
	
	public Boolean getSortByConnections()
	{
		return sortByConnections;
	}
	
	public int getDepartureMonth()
	{
		return instance.getDepartureDate().get(Calendar.MONTH) + 1;
	}
	
	public int getDepartureYear()
	{
		return instance.getDepartureDate().get(Calendar.YEAR);
	}
	
	public int getDepartureDay()
	{
		return instance.getDepartureDate().get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * Checks to see if an inputted text is potentially an airport code.
	 * @param possibleSearchItem : the text that we are checking
	 * @return Whether or not the text was found to be an airport code
	 */
	private static Boolean isPotentialAirportCode(String possibleSearchItem)
	{
		Boolean isAirportCode = true;
		if(possibleSearchItem.length() != 3)
		{
			isAirportCode = false;
		} 
		else if (!findAirportCode(possibleSearchItem))
		{
			isAirportCode = false;
		}
		return isAirportCode;
	}
	
	/**
	 * Checks to see if the users three character entered text is a valid airport code taht is found in our database.
	 * @param possibleAirportCode : text that is being checked to see if it is an airport code
	 * @return Whether or not the inputted text is an airport code found in our database
	 */
	private static Boolean findAirportCode(String possibleAirportCode)
	{
		Boolean findAirportCode = false;
		try
		{
			PreparedStatement statement = SQLInitializer.getDatabaseConnection().prepareStatement("SELECT COUNT(acode) FROM airports WHERE acode = ? GROUP BY acode");
			statement.setString(1, possibleAirportCode);
			ResultSet result = statement.executeQuery();
			int count = result.getInt(1);
			if (count >= 1)
			{
				findAirportCode = true;
			}
			SQLInitializer.closeConnection();
		} 
		catch (SQLException ex)
		{
			System.err.print("SQLException: " + ex.getMessage());
		}
		return findAirportCode;
	}
	
	/**
	 * Sets all needed information for a FlightSearch.
	 * @param depDate
	 * @param depCity
	 * @param arrCity
	 * @param rndTrip
	 */
	public void createFlightSearch(Calendar depDate, String depCity, String arrCity, Boolean rndTrip)
	{
		departureDate = depDate;
		departureCity = depCity;
		arrivalCity = arrCity;
		roundTrip = rndTrip;
	}
	
	/**
	 * Return the desired departure date of the flight search.
	 * @return
	 */
	public Calendar getDepartureDate()
	{
		return departureDate;
	}
	
	/**
	 * Returns the destination airport code of the flight search.
	 * @return
	 */
	public String getArrivalCity()
	{
		return arrivalCity;
	}
	
	/**
	 * Returns the source airport code of the flight search.
	 * @return
	 */
	public String getDepartureCity()
	{
		return departureCity;
	}
	
	/**
	 * Sets the sortByConnections variable
	 * @param byConnections : the desired boolean value of sortByConnections
	 */
	public void setSortByConnections(Boolean byConnections)
	{
		sortByConnections = byConnections;
	}
	
	/**
	 * INCOMPLETE: Will return the query for the user entered search.
	 * @return
	 */
	public PreparedStatement getSearchQuery()
	{
		//TODO:FINISH THIS.
		Connection currentDBConnection = SQLInitializer.getDatabaseConnection();
		PreparedStatement myQueryStatement = null;
		try
		{
			myQueryStatement = currentDBConnection.prepareStatement("");
		} 
		catch (SQLException exception)
		{
			
		}
		return myQueryStatement;
	}
	
	/**
	 * Generates the prepared SQL statement that will be used to query potential text matches to the user input.
	 * @param connection : The connection to the database
	 * @param textToFind : Text that the user entered which we're searching for in the database
	 * @return the query that should return airports that match the entered text
	 */
	private static String getPotentialAirportMatchesQuery(Connection connection, String textToFind)
	{
		String selectStatement = null;
		/**if(isPotentialAirportCode(textToFind))
		{
			selectStatement = "SELECT acode, name FROM airports WHERE UPPER(acode) = " + textToFind.toUpperCase();
		} 
		else 
		{ **/
			selectStatement = "SELECT acode, name, city FROM airports WHERE UPPER(name) LIKE '" + textToFind.toUpperCase() + "%' OR UPPER(city) LIKE '" + textToFind.toUpperCase() + "%' OR UPPER(acode) LIKE '" + textToFind.toUpperCase() + "%'";
		//}
		return selectStatement;
	}

/**
 * Searches the database to grab potential cities that the user may be typing in.
 * @param text : what the user has typed into the input box
 * @param menuToAddTo : The menu we are adding items to
 * @return the results from the database of potential city matches to the entered text
 */
	public static List<MenuItem> findPossibleAirportDatabaseMatches(String text, Menu menuToAddTo, final Text mainTextBox) {
		// TODO Auto-generated method stub
		List<MenuItem> possibleMatches = new ArrayList<MenuItem>();
		Connection connection = SQLInitializer.getDatabaseConnection();
		String statement = getPotentialAirportMatchesQuery(connection, text);
		try
		{
			ResultSet resultSet = SQLInitializer.executeQuery(statement);
			if(resultSet != null){
				while(resultSet.next())
				{
					final MenuItem newItem = new MenuItem(menuToAddTo, SWT.PUSH);
					newItem.setText(String.format("%s, %s , %s", resultSet.getString("acode"), resultSet.getString("name"), resultSet.getString("city")));
					newItem.addListener(SWT.Selection, new Listener()
					{
						@Override
						public void handleEvent(Event arg0)
						{
							int index = newItem.getText().indexOf(",");
							mainTextBox.setText(newItem.getText().substring(0, index));
						}
					});
				}
				SQLInitializer.closeConnection();
			}
		} 
		catch (SQLException ex)
		{
			
		}
		return possibleMatches;
	}
	
	public static void FindSearchedFlights(Table table) 
	{
		// TODO Auto-generated method stub
		String query = formatSearchIntoQuery(table);
		
	}

	private static String formatSearchIntoQuery(Table table) 
	{
		// TODO Auto-generated method stub
		String directFlightQuery = "";
		FlightSearch search = FlightSearch.getInstance();
		List<Flight> oneConnectionFlightResults = new ArrayList<Flight>();
		if(search.getSortByConnections())
		{
			directFlightQuery = findDirectFlightsQuery(FlightSearch.getInstance());
			oneConnectionFlightResults = findOneConnectionFlightsByPrice(FlightSearch.getInstance());
		} 
		else 
		{
			directFlightQuery = findDirectFlightsQueryByPrice(FlightSearch.getInstance());
			oneConnectionFlightResults = findOneConnectionFlightsByPrice(FlightSearch.getInstance());
		}
		List<Flight> directFlightResults = getDirectFlightResults(directFlightQuery, FlightSearch.getInstance());
		PopulateTableWithResults(directFlightResults, oneConnectionFlightResults, null, table);
		return "";
	}
	
	private static void PopulateTableWithResults(List<Flight> directFlightResults, List<Flight> oneConnectionFlightResults, List<Flight> twoConnectionFlightResults, Table table) 
	{
		// TODO Auto-generated method stub
		if(FlightSearch.getInstance().getSortByConnections())
		{
			List<Flight> combinedResults = new ArrayList<Flight>();
			combinedResults.addAll(directFlightResults);
			combinedResults.addAll(oneConnectionFlightResults);
			for (int i = 0; i < combinedResults.size(); i++)
			{
				Flight flight = combinedResults.get(i);
				TableItem tableItem = new TableItem(table, SWT.NONE);
				String tableLabel = "";
				for (int j = 1; j <= flight.getColumnNumber(); j++) {
	                // Populate the item
					tableLabel = tableLabel + flight.getColumnItem(j) + " ";
	            }
				tableItem.setText(0, tableLabel);
			}
			FlightResult result = FlightResult.getInstance();
			result.setFlightResult(combinedResults);
		} 
		else 
		{
			List<Flight> combinedResults = combineFlightsForLowestPrice(directFlightResults, oneConnectionFlightResults);
			for (int i = 0; i < combinedResults.size(); i++)
			{
				Flight flight = combinedResults.get(i);
				TableItem tableItem = new TableItem(table, SWT.NONE);
				String tableLabel = "";
				for (int j = 1; j <= flight.getColumnNumber(); j++) {
	                // Populate the item
	                //tableItem.setText(i - 1, flight.getColumnItem(i));
					tableLabel = tableLabel + flight.getColumnItem(j) + " ";
	            }
                tableItem.setText(0, tableLabel);
			}
			FlightResult result = FlightResult.getInstance();
			result.setFlightResult(combinedResults);
		}
	}
	
	private static List<Flight> combineFlightsForLowestPrice(List<Flight> directFlightResults, List<Flight> oneConnectionFlightResults)
	{
		List<Flight> combined = new ArrayList<Flight>();
		int i = 0;
		int j = 0;
		while(j < oneConnectionFlightResults.size() && i < directFlightResults.size())
		{
			if(directFlightResults.get(i).getPrice() <= oneConnectionFlightResults.get(j).getPrice())
			{
				combined.add(directFlightResults.get(i));
				i++;
			} 
			else if (directFlightResults.get(i).getPrice() >= oneConnectionFlightResults.get(j).getPrice())
			{
				combined.add(oneConnectionFlightResults.get(j));
				j++;
			}
		}
		while(j < oneConnectionFlightResults.size() - 1)
		{
			combined.add(oneConnectionFlightResults.get(j));
			j++;
		}
		while(i < directFlightResults.size() - 1)
		{
			combined.add(directFlightResults.get(i));
			i++;
		}
		return combined;
	}

	private static List<Flight> getDirectFlightResults(String directFlightQuery, FlightSearch search) {
		// TODO Auto-generated method stub
		SQLInitializer.closeConnection();
		List<Flight> flights = new ArrayList<Flight>();
		try
		{
			flights = new ArrayList<Flight>();
			ResultSet resultSet = SQLInitializer.executeQuery(directFlightQuery);
			while(resultSet.next())
			{
				List<String> flightNo = new ArrayList<String>();
				List<String> fare = new ArrayList<String>();
				flightNo.add(resultSet.getString("flightno"));
				fare.add(resultSet.getString("fare_type"));
				Flight newFlight = new Flight(resultSet.getString("src"), resultSet.getString("dst"), resultSet.getDate("dep_time"), resultSet.getDate("arr_time"), 0, flightNo, fare, null, resultSet.getFloat("price"));
				flights.add(newFlight);
			}
			SQLInitializer.closeConnection();
		} 
		catch (SQLException ex)
		{
			
		}
		return flights;
	}
	
	private PreparedStatement createBasicFlightSearchPreparedStatement(String flightsQuery, Connection connection, FlightSearch search)
	{
		PreparedStatement statement = null;
		try	
		{
			statement = connection.prepareStatement(flightsQuery);
			statement.setString(1, search.getDepartureCity());
			statement.setString(2, search.getArrivalCity());
			statement.setInt(3, search.getDepartureDate().get(Calendar.DAY_OF_MONTH));
			statement.setInt(4, search.getDepartureDate().get(Calendar.MONTH));
			statement.setInt(5, search.getDepartureDate().get(Calendar.YEAR));
		} 
		catch (SQLException ex)
		{
			
		}
		return statement;
	}

	private static String findDirectFlightsQuery(FlightSearch search)
	{
		String query = new StringBuilder()
				  .append("select f.flightno as flightno, sf.dep_date as dep_date, f.src as src, f.dst as dst, f.dep_time+(trunc(sf.dep_date)-trunc(f.dep_time)) as dep_time," )
					.append(" f.dep_time+(trunc(sf.dep_date)-trunc(f.dep_time))+(f.est_dur/60+a2.tzone-a1.tzone)/24 as arr_time,") 
				    .append(" fa.fare as fare_type, fa.limit-count(tno) as available_seats, fa.price as price")
				 .append(" from flights f, flight_fares fa, sch_flights sf, bookings b, airports a1, airports a2")
				 .append(" where f.flightno=sf.flightno and f.flightno=fa.flightno and f.src=a1.acode and")
					.append(" f.dst=a2.acode and fa.flightno=b.flightno(+) and fa.fare=b.fare(+) and")
					.append(" sf.dep_date=b.dep_date(+)")
				  .append(" group by f.flightno, sf.dep_date, f.src, f.dst, f.dep_time, f.est_dur,a2.tzone,")
					.append(" a1.tzone, fa.fare, fa.limit, fa.price")
				  .append(" having fa.limit-count(tno) > 0")
				  .append(" and f.src = '" + search.getDepartureCity() + "' and f.dst = '" + search.getArrivalCity() + "' and extract(day from sf.dep_date) = " + search.getDepartureDate().get(Calendar.DAY_OF_MONTH) + " and extract(month from sf.dep_date) = " + search.getDepartureDate().get(Calendar.MONTH) + " and extract(year from sf.dep_date) = " + search.getDepartureDate().get(Calendar.YEAR) + "").toString();
		//connection.prepareStatement("select f.flightno, sf.dep_date, f.src, f.dst, f.dep_time+(trunc(sf.dep_date)-trunc(f.dep_time)) as dep_time, f.dep_time+(trunc(sf.dep_date)-trunc(f.dep_time))+(f.est_dur/60+a2.tzone-a1.tzone)/24 as arr_time, fa.fare as fare, fa.limit-count(tno) as available_seats, fa.price as price from flights f, flight_fares fa, sch_flights sf, bookings b, airports a1, airports a2 where f.flightno=sf.flightno and f.flightno=fa.flightno and f.src=a1.acode and f.dst=a2.acode and fa.flightno=b.flightno(+) and fa.fare=b.fare(+) and sf.dep_date=b.dep_date(+)group by f.flightno, sf.dep_date, f.src, f.dst, f.dep_time, f.est_dur,a2.tzone, a1.tzone, fa.fare, fa.limit, fa.price HAVING fa.limit-count(tno) > 0 and f.src = 'YEG' and f.dst = 'YYZ';)"
		return query;
	}
	
	private static List<Flight> findOneConnectionFlightsByPrice(FlightSearch search)
	{
		SQLInitializer.closeConnection();
		String createAvailableFlightsView = new StringBuilder()
		  .append("create view available_flights(flightno,dep_date, src,dst,dep_time,arr_time,fare,seats, price) as ")
		  .append("select f.flightno as flightno, sf.dep_date as dep_date, f.src as src, f.dst as dst, f.dep_time+(trunc(sf.dep_date)-trunc(f.dep_time)) as dep_time," )
			.append(" f.dep_time+(trunc(sf.dep_date)-trunc(f.dep_time))+(f.est_dur/60+a2.tzone-a1.tzone)/24 as arr_time,") 
		    .append(" fa.fare as fare_type, fa.limit-count(tno) as available_seats, fa.price as price")
		 .append(" from flights f, flight_fares fa, sch_flights sf, bookings b, airports a1, airports a2")
		 .append(" where f.flightno=sf.flightno and f.flightno=fa.flightno and f.src=a1.acode and")
			.append(" f.dst=a2.acode and fa.flightno=b.flightno(+) and fa.fare=b.fare(+) and")
			.append(" sf.dep_date=b.dep_date(+)")
		  .append(" group by f.flightno, sf.dep_date, f.src, f.dst, f.dep_time, f.est_dur,a2.tzone,")
			.append(" a1.tzone, fa.fare, fa.limit, fa.price")
		  .append(" having fa.limit-count(tno) > 0")
		  .append(" order by fa.price").toString();
		
		String getRoundTripFlights = new StringBuilder()
				.append("select a1.src, a2.dst, a1.dep_date, a2.arr_time, a1.flightno as flight1, a2.flightno as flight2, a2.dep_time-a1.arr_time as layover, ")
				.append("min(a1.price+a2.price) as price, a1.fare as fare_type ")
			  .append("from available_flights a1, available_flights a2 ")
			  .append("where a1.dst=a2.src and a1.arr_time +1.5/24 <=a2.dep_time and a1.arr_time +5/24 >=a2.dep_time ")
			  .append("and a1.src = '" + search.getDepartureCity() + "' and a2.dst = '" + search.getArrivalCity() + "'  and extract(day from a1.dep_date) = " + search.getDepartureDay() + " and extract(month from a1.dep_date) = " + search.getDepartureMonth() + " and extract(year from a1.dep_date) = " + search.getDepartureYear() + " ")
			  .append("group by a1.src, a2.dst, a1.dep_date, a1.flightno, a2.flightno, a2.dep_time, a1.arr_time, a1.fare, a2.fare, a2.arr_time ")
			  .append("order by min(a1.price+a2.price)").toString();
		List<Flight> flights = new ArrayList<Flight>();
		try{
			SQLInitializer.executeQuery(createAvailableFlightsView);
			ResultSet resultSet = SQLInitializer.executeQuery(getRoundTripFlights);
			if(resultSet != null)
			{
				while(resultSet.next())
				{
					List<String> flightNo = new ArrayList<String>();
					List<String> fare = new ArrayList<String>();
					flightNo.add(resultSet.getString("flight1"));
					flightNo.add(resultSet.getString("flight2"));
					fare.add(resultSet.getString("fare_type"));
					Float layover = resultSet.getFloat("layover");
					Float price = resultSet.getFloat("price");
					try{
						Flight newFlight = new Flight(resultSet.getString("src"), resultSet.getString("dst"), resultSet.getDate("dep_date"), resultSet.getDate("arr_time"), 1, flightNo, fare, layover, price);
						flights.add(newFlight);
					} catch (Exception e)
					{
						String error = e.getMessage();
					}

				}
			}
		} catch (SQLException e){
			
		}
		
		
		SQLInitializer.executeQuery("drop view available_flights");
		SQLInitializer.closeConnection();
		return flights;
	}
	
	private static String findDirectFlightsQueryByPrice(FlightSearch search)
	{
		String query = new StringBuilder()
				  .append("select f.flightno as flightno, sf.dep_date as dep_date, f.src as src, f.dst as dst, f.dep_time+(trunc(sf.dep_date)-trunc(f.dep_time)) as dep_time," )
					.append(" f.dep_time+(trunc(sf.dep_date)-trunc(f.dep_time))+(f.est_dur/60+a2.tzone-a1.tzone)/24 as arr_time,") 
				    .append(" fa.fare as fare_type, fa.limit-count(tno) as available_seats, fa.price as price")
				 .append(" from flights f, flight_fares fa, sch_flights sf, bookings b, airports a1, airports a2")
				 .append(" where f.flightno=sf.flightno and f.flightno=fa.flightno and f.src=a1.acode and")
					.append(" f.dst=a2.acode and fa.flightno=b.flightno(+) and fa.fare=b.fare(+) and")
					.append(" sf.dep_date=b.dep_date(+)")
				  .append(" group by f.flightno, sf.dep_date, f.src, f.dst, f.dep_time, f.est_dur,a2.tzone,")
					.append(" a1.tzone, fa.fare, fa.limit, fa.price")
				  .append(" having fa.limit-count(tno) > 0")
				  .append(" and f.src = '" + search.getDepartureCity() + "' and f.dst = '" + search.getArrivalCity() + "' and extract(day from sf.dep_date) = " + search.getDepartureDay() + " and extract(month from sf.dep_date) = " + search.getDepartureMonth() + " and extract(year from sf.dep_date) = " + search.getDepartureYear() + "")
				  .append(" order by fa.price").toString();
		//connection.prepareStatement("select f.flightno, sf.dep_date, f.src, f.dst, f.dep_time+(trunc(sf.dep_date)-trunc(f.dep_time)) as dep_time, f.dep_time+(trunc(sf.dep_date)-trunc(f.dep_time))+(f.est_dur/60+a2.tzone-a1.tzone)/24 as arr_time, fa.fare as fare, fa.limit-count(tno) as available_seats, fa.price as price from flights f, flight_fares fa, sch_flights sf, bookings b, airports a1, airports a2 where f.flightno=sf.flightno and f.flightno=fa.flightno and f.src=a1.acode and f.dst=a2.acode and fa.flightno=b.flightno(+) and fa.fare=b.fare(+) and sf.dep_date=b.dep_date(+)group by f.flightno, sf.dep_date, f.src, f.dst, f.dep_time, f.est_dur,a2.tzone, a1.tzone, fa.fare, fa.limit, fa.price HAVING fa.limit-count(tno) > 0 and f.src = 'YEG' and f.dst = 'YYZ';)"
		return query;
	}
	
	
}
