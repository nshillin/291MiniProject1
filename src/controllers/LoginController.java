package controllers;

import models.User;
import views.LoginView;
import views.Test;

public class LoginController {

	private static LoginView loginView = new LoginView();

	public static void main(String[] args) {
		new Test();
	/*	loginView.clearScreen();
		if (loginView.loginOrRegister() == loginView.LOGIN) {
			loginScreen();
		}
		else {
			registerScreen();
		}
		*/
	}
	
	private static void loginScreen() {
		loginView.clearScreen();
		String username = loginView.getUserName();
		User.setUser(username);
		String password = loginView.getPassword();
		QueryHandler.authenticateUser(password);
	}
	
	private static void registerScreen() {
		loginView.clearScreen();

	}

}
