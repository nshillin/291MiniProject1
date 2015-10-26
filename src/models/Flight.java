package models;

import java.sql.Connection;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import controllers.SQLInitializer;

public class Flight 
{
	private String flightSource;
	private String flightDestination;
	private Date flightDepartureTime;
	private Date flightArrivalTime;
	private Integer numberOfConnections;
	private List<String> flightNumber;
	private Time layoverTime;
	
	private enum seatPrices{}; //need to double check if there are even different fare types
	private enum numberOfSeatsAtPrice{};
	
	public Flight(String src, String dst, Date depTime, Date arrTime, Integer numOfConnections, List<String> flightNum, Time layover)
	{
		String flightSource = src;
		String flightDestination = dst;
		Date flightDepartureTime = depTime;
		Date flightArrivalTime = arrTime;
		List<String> flightNumber = flightNum;
		Integer numberOfConnections = numOfConnections;
		Time layoverTime = layover;
	}
	
	public Integer getColumnNumber()
	{
		return 7;
	}
	
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
			returnColumnValue = flightArrivalTime.toString();
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
			returnColumnValue = layoverTime.toString();
		}
		return returnColumnValue;
	}

	public static void FindSearchedFlights() 
	{
		// TODO Auto-generated method stub
		String query = formatSearchIntoQuery();
		
	}

	private static String formatSearchIntoQuery() 
	{
		// TODO Auto-generated method stub
		FlightSearch searchParameters = FlightSearch.getInstance();
		String directFlightQuery = findDirectFlightsQuery(searchParameters);
		return "";
	}
	
	private static String findDirectFlightsQuery(FlightSearch search)
	{
		String query = new StringBuilder()
				  .append("select f.flightno, sf.dep_date, f.src, f.dst, f.dep_time+(trunc(sf.dep_date)-trunc(f.dep_time)) as dep_time," )
					.append(" f.dep_time+(trunc(sf.dep_date)-trunc(f.dep_time))+(f.est_dur/60+a2.tzone-a1.tzone)/24 as arr_time,") 
				    .append(" fa.fare, fa.limit-count(tno) as available_seats, fa.price")
				 .append(" from flights f, flight_fares fa, sch_flights sf, bookings b, airports a1, airports a2")
				 .append(" where f.flightno=sf.flightno and f.flightno=fa.flightno and f.src=a1.acode and")
					.append(" f.dst=a2.acode and fa.flightno=b.flightno(+) and fa.fare=b.fare(+) and")
					.append(" sf.dep_date=b.dep_date(+)")
				  .append(" group by f.flightno, sf.dep_date, f.src, f.dst, f.dep_time, f.est_dur, a2.tzone,")
					.append(" a1.tzone, fa.fare, fa.limit, fa.price")
				  .append(" having fa.limit-count(tno) > 0")
				  .append(" and f.src = ? and f.dst = ? and extract(day from sf.dep_date) = ? and extract(month from sf.dep_date) = ? and extract(year from sf.dep_date) = ?").toString();
		//connection.prepareStatement("select f.flightno, sf.dep_date, f.src, f.dst, f.dep_time+(trunc(sf.dep_date)-trunc(f.dep_time)) as dep_time, f.dep_time+(trunc(sf.dep_date)-trunc(f.dep_time))+(f.est_dur/60+a2.tzone-a1.tzone)/24 as arr_time, fa.fare as fare, fa.limit-count(tno) as available_seats, fa.price as price from flights f, flight_fares fa, sch_flights sf, bookings b, airports a1, airports a2 where f.flightno=sf.flightno and f.flightno=fa.flightno and f.src=a1.acode and f.dst=a2.acode and fa.flightno=b.flightno(+) and fa.fare=b.fare(+) and sf.dep_date=b.dep_date(+)group by f.flightno, sf.dep_date, f.src, f.dst, f.dep_time, f.est_dur,a2.tzone, a1.tzone, fa.fare, fa.limit, fa.price HAVING fa.limit-count(tno) > 0 and f.src = 'YEG' and f.dst = 'YYZ';)"
		return query;
	}
}
