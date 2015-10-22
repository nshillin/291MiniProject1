package models;

public class User {
	private static String userName;
	private static Boolean airlineAgent = true;
	
	public static void setUser(String userName) {
		User.userName = userName;
	}
	
	public static String getUser() {
		return userName;
	}
	
	public static void setAirlineAgent(Boolean airlineAgent) {
		User.airlineAgent = airlineAgent;
	}
	
	public static Boolean isAirlineAgent(){
		return airlineAgent;
	}

}
