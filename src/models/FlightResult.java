package models;

import java.util.List;

public class FlightResult 
{
	private static FlightResult instance;
	private List<Flight> listOfFlightResults;
	
	public static FlightResult getInstance()
	{
		if(instance == null)
		{
			instance = new FlightResult();
		}
		return instance;
	}
	
	private FlightResult()
	{
		
	}
	
	public void setFlightResult(List<Flight> flights)
	{
		listOfFlightResults = flights;
	}
	
	public List<Flight> getFlightResults()
	{
		return listOfFlightResults;
	}
}
