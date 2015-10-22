package controllers;

import models.User;

public class QueryHandler {

	public static void main(String[] args) {
		// Setup SQL stuff	
	}
	
	public static Boolean authenticateUser(String password) {
		// Checks SQL for user
		String user = User.getUser();
		return true;
	}

	public static void addUser(String username, String password) {
		
	}
	
	public static Boolean isUsername() {
		//Checks if username already exists
		return true;
	}
}
