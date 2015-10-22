package controllers;

import models.User;
import views.LoginView;
import views.MainView;

public class LoginController {

	private static LoginView loginView = new LoginView(null);

	public static void main(String[] args) {
		MainView.main(args);
		MainView.newShell(loginView);
	}
	
	private static void loginScreen() {
		String username = loginView.getUserName();
		User.setUser(username);
		String password = loginView.getPassword();
		QueryHandler.authenticateUser(password);
	}
	
	private static void registerScreen() {
		String username = loginView.getUserName();
		String password = loginView.getPassword();
		QueryHandler.addUser(username,password);
	}

}
