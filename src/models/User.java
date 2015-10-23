package models;

public class User {
	private static String username;
	private static Boolean airlineAgent = false;
	
	public static void setUser(String username) {
		User.username = username;
	}
	
	public static String getUser() {
		return username;
	}
	
	public static void setAirlineAgent(Boolean airlineAgent) {
		User.airlineAgent = airlineAgent;
	}
	
	public static Boolean isAirlineAgent(){
		return airlineAgent;
	}

}
