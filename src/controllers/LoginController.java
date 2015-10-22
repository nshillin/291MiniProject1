package controllers;

import models.User;
import views.LoginView;

public class LoginController {

	public static void main(String[] args) {
		LoginView loginView = new LoginView();
		loginView.clearScreen();
		User.setUser(loginView.getUserName());
		loginView.getPassword();
	}

}
