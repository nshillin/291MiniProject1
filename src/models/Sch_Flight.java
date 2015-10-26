package models;

import java.sql.Date;

public class Sch_Flight {
	private String flightNumber;
	private Date departureDate;
	private Date act_dep_time;
	private Date act_arr_time;
	
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public Date getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}
	public Date getAct_dep_time() {
		return act_dep_time;
	}
	public void setAct_dep_time(Date act_dep_time) {
		this.act_dep_time = act_dep_time;
	}
	public Date getAct_arr_time() {
		return act_arr_time;
	}
	public void setAct_arr_time(Date act_arr_time) {
		this.act_arr_time = act_arr_time;
	}
	
	

}
