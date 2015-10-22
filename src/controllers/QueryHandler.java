package controllers;

import models.User;
import views.LoginView;

public class QueryHandler {

	public static void main(String[] args) {
		// Setup SQL stuff	
	}
	
	public static Boolean authenticateUser(String password) {
		// Checks SQL for user
		String user = User.getUser();
		return true;
	}
	
	public static Boolean addUser(String password) {
		// Checks SQL for user
		String user = User.getUser();
		return true;
	}

	public static void addUser(String username, String password) {
		// TODO Auto-generated method stub
		
	}
}
