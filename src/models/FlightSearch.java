	package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;

import controllers.SQLInitializer;

public class FlightSearch {
	
	private static FlightSearch instance = null;
	private String airportCode;
	private Date departureDate;
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
	public void createFlightSearch(Date depDate, String depCity, String arrCity, Boolean rndTrip)
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
	public Date getDepartureDate()
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
		/*if(isPotentialAirportCode(textToFind))
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
			while(resultSet.next())
			{
				final MenuItem newItem = new MenuItem(menuToAddTo, SWT.PUSH);
				newItem.setText(String.format("%s , %s , %s", resultSet.getString("acode"), resultSet.getString("name"), resultSet.getString("city")));
				newItem.addListener(SWT.Selection, new Listener()
				{
					@Override
					public void handleEvent(Event arg0)
					{
						mainTextBox.setText(newItem.getText());
					}
				});
			}
		} 
		catch (SQLException ex)
		{
			
		}
		return possibleMatches;
	}
	
}
