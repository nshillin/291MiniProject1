package models;

import java.util.Date;

public class FlightSearch {
	
	private static FlightSearch instance = null;
	private String airportCode;
	private Date departureDate;
	private String departureCity;
	private String arrivalCity;
	private Boolean roundTrip;
	private Boolean sortByConnections;
	
	protected FlightSearch()
	{
	}
	
	public static FlightSearch getInstance(){
		if(instance == null)
		{
			instance = new FlightSearch();
		}
		return instance;
	}
	
	public void createFlightSearch(Date depDate, String depCity, String arrCity, Boolean rndTrip)
	{
		departureDate = depDate;
		departureCity = depCity;
		arrivalCity = arrCity;
		roundTrip = rndTrip;
	}
	
	public Date getDepartureDate()
	{
		return departureDate;
	}
	
	public String getArrivalCity()
	{
		return arrivalCity;
	}
	
	public String getDepartureCity()
	{
		return departureCity;
	}
	
	public void setSortByConnections(Boolean byConnections)
	{
		sortByConnections = byConnections;
	}
	
	public String getSearchQuery()
	{
		return "";
	}
	
}
