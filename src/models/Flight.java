package models;

import java.util.Calendar;
import java.sql.Date;
import java.util.List;

public class Flight 
{
	private String flightSource;
	private String flightDestination;
	private Date flightDepartureTime;
	private Calendar flightDepartureTimeCal;
	private Calendar flightArrivalTime;
	private Integer numberOfConnections;
	private List<String> flightNumber;
	private List<String> fare;
	private Float layoverTime;
	private Float paid_price;
	
	/**
	 * Constructor for a flight object.
	 * @param src : where flight starts
	 * @param dst : desired destination
	 * @param depTime : when flight departs
	 * @param arrTime : when flight arrives at its destination
	 * @param numOfConnections : how many connections a flight has between its start and destination
	 * @param flightNum : the flight number
	 * @param fare : the fare type of a flight
	 * @param layover : the time spent in a layover
	 * @param price : price per seat
	 */
	public Flight(String src, String dst, Date depTime, Date arrTime, Integer numOfConnections, List<String> flightNum, List<String> fare, Float layover, Float price)
	{
		this.flightSource = src;
		this.flightDestination = dst;
		this.flightDepartureTime = depTime;
		this.flightDepartureTimeCal = Calendar.getInstance();
		flightDepartureTimeCal.setTime(depTime);
		this.flightArrivalTime = Calendar.getInstance();
		flightArrivalTime.setTime(arrTime);
		this.flightNumber = flightNum;
		this.fare = fare;
		this.numberOfConnections = numOfConnections;
		this.layoverTime = layover;
		this.paid_price = price;
	}
	
	/**
	 * Number of parameters to display in the results screen.
	 * @return
	 */
	public static Integer getColumnNumber()
	{
		return 8;
	}
	
	/**
	 * Returns the price of the seat.
	 * @return
	 */
	public Float getPrice()
	{
		return this.paid_price;
	}
	
	/**
	 * Returns the flight numbers that are a part of each voyage if the flight is not direct.
	 * @return
	 */
	public List<String> getFlightNums()
	{
		return this.flightNumber;
	}
	
	/**
	 * Returns the departure date of the flight.
	 * @return
	 */
	public Date getDepDate() {
		return this.flightDepartureTime;
	}
	
	/**
	 * Returns the calendar object departure date of the Flight.
	 * @return
	 */
	public Calendar getDepDate_Cal() {
		return this.flightDepartureTimeCal;
	}
	
	/**
	 * Returns the fare type of the selected flight.
	 * @return
	 */
	public List<String> getFare() {
		return this.fare;
	}
	
	/**
	 * Returns the appropriately formatted parameter of the desired column.
	 * @param columnNumber the position of the parameter in the desired string.
	 * @return
	 */
	public String getColumnItem(int columnNumber)
	{
		String returnColumnValue = "";
		if(columnNumber == 1){
			returnColumnValue = flightSource;
		} 
		else if(columnNumber == 2)
		{
			returnColumnValue = flightDestination;
		}
		else if(columnNumber == 3)
		{
			returnColumnValue = flightDepartureTime.toString();
		}
		else if(columnNumber == 4)
		{
			returnColumnValue = flightArrivalTime.getTime().toString();
		}
		else if(columnNumber == 5)
		{
			for (int i= 0; i < flightNumber.size(); i++){
				returnColumnValue += flightNumber.get(i) + " ";
			}
		}
		else if(columnNumber == 6)
		{
			returnColumnValue = numberOfConnections.toString();
		}
		else if(columnNumber == 7)
		{
			if(layoverTime != null)
			{
				returnColumnValue = layoverTime.toString();
			} 
			else 
			{
				returnColumnValue = "Direct";
			}
		}
		else if(columnNumber == 8)
		{
			returnColumnValue = paid_price.toString();
		}
		return returnColumnValue;
	}
}
