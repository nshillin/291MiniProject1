package models;

import java.sql.Time;
import java.util.Date;

public class Flight 
{
	private String flightSource;
	private String flightDestination;
	private Date flightDepartureTime;
	private Date flightArrivalTime;
	private Integer numberOfConnections;
	private String flightNumber;
	private Time layoverTime;
	
	private enum seatPrices{}; //need to double check if there are even different fare types
	private enum numberOfSeatsAtPrice{};
	
	public Flight()
	{
		
	}
	
}
