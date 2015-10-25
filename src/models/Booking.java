package models;

import java.sql.Date;

public class Booking {
	//ticket number, the passenger name, the departure date and the price
	private Integer ticketNumber;
	private String flightNumber;
	private String fare;
	private String seat;
	private String pName;
	private Date depDate;
	private Float price;
	
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getFare() {
		return fare;
	}
	public void setFare(String fare) {
		this.fare = fare;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}
	
	public int getTicketNumber() {
		return ticketNumber;
	}
	public void setTicketNumber(Integer ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public Date getDepDate() {
		return depDate;
	}
	public void setDepDate(Date depDate) {
		this.depDate = depDate;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
}
